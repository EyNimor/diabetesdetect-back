package com.mediscreen.diabetesdetect.main.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;

import com.mediscreen.diabetesdetect.main.constant.Sex;
import com.mediscreen.diabetesdetect.main.feign.HistoryClient;
import com.mediscreen.diabetesdetect.main.feign.PatientClient;
import com.mediscreen.diabetesdetect.main.mock.MockedHistoryFeignClient;
import com.mediscreen.diabetesdetect.main.mock.MockedPatientFeignClient;
import com.mediscreen.diabetesdetect.main.model.Note;
import com.mediscreen.diabetesdetect.main.model.Patient;
import com.mediscreen.diabetesdetect.main.service.MainService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MainServiceTest {

    private static PatientClient patientClient;
    private static HistoryClient historyClient;

    private static MainService service;

    Patient p1, p2, p3, p4, updatedP3, updatedP4;
    List<Patient> patientList;
    MultiValueMap<String, String> newP3, newP4, completeUpdateP3, partialUpdateP4 ;

    Note p1n1, p1n2, p2n1, p2n2, p3n1, p4n1, updatedP4N1;
    List<Note> history;
    MultiValueMap<String, String> newP3N1, newP4N1, updateP4N1;

    @PostConstruct
    private void setUp() {
        p1 = new Patient("One", "Test", LocalDate.of(1990, 12, 15), Sex.MEN, "Address 1", "Phone");
        p2 = new Patient("Two", "Test", LocalDate.of(1991, 11, 1), Sex.MEN, "Address 2", "Phone");
        p3 = new Patient("Three", "Test", LocalDate.of(1992, 4, 30), Sex.WOMEN, "Address 3", "Phone");
        p4 = new Patient("Four", "Test", LocalDate.of(1993, 6, 24), Sex.WOMEN, "Address 4", "Phone");
        
        updatedP3 = p3;
        updatedP3.setLastName("Five");
        updatedP3.setFirstName("Updated");
        updatedP3.setBirthDate(LocalDate.of(1992, 5, 23));
        updatedP3.setSex(Sex.MEN);
        updatedP3.setAddress("Address Updated");
        updatedP3.setPhone("Phone Updated");
        updatedP4 = p4;
        updatedP4.setLastName("Six");
        updatedP4.setFirstName("Updated");
        updatedP4.setAddress("Address Updated");
        
        patientList = new ArrayList<>();
        patientList.add(p1);
        patientList.add(p2);

        newP3 = initPatientCreationMap(p3);
        newP4 = initPatientCreationMap(p4);
        completeUpdateP3 = initPatientCreationMap(p3);
        partialUpdateP4 = new HttpHeaders();
        partialUpdateP4.add("family", updatedP4.getLastName());
        partialUpdateP4.add("given", updatedP4.getFirstName());
        partialUpdateP4.add("address", updatedP4.getAddress());

        p1n1 = new Note(p1.getPatientId(), "Note 1 pour le Patient 1.");
        p1n2 = new Note(p1.getPatientId(), "Note 2 pour le Patient 1.");
        p2n1 = new Note(p2.getPatientId(), "Note 1 pour le Patient 2.");
        p2n2 = new Note(p2.getPatientId(), "Note 2 pour le Patient 2.");
        p3n1 = new Note(p3.getPatientId(), "Nouvelle Note pour le Patient 3.");
        p4n1 = new Note(p4.getPatientId(), "Nouvelle note pour le Patient 4.");

        updatedP4N1 = p4n1;
        updatedP4N1.setBody("Note mise à jour pour le Patient 4.");

        history = new ArrayList<>();
        history.add(p1n1);
        history.add(p1n2);
        history.add(p2n1);
        history.add(p2n2);

        newP3N1 = initHistoryNoteCreationMap(p3n1);
        newP4N1 = initHistoryNoteCreationMap(p4n1);
        updateP4N1 = new HttpHeaders();
        updateP4N1.add("e", updatedP4N1.getBody());

        patientClient = new MockedPatientFeignClient(patientList);
        historyClient = new MockedHistoryFeignClient(history);

        service = new MainService(patientClient, historyClient);
    }

    private MultiValueMap<String, String> initPatientCreationMap(Patient p) {
        MultiValueMap<String, String> map = new HttpHeaders();
        map.add("family", p.getLastName());
        map.add("given", p.getFirstName());
        map.add("dob", p.getBirthDate().toString());
        map.add("sex", p.getSex().toString());
        map.add("address", p.getAddress());
        map.add("phone", p.getPhone());
        return map;
    }

    private MultiValueMap<String, String> initHistoryNoteCreationMap(Note n) {
        MultiValueMap<String, String> map = new HttpHeaders();
        map.add("patId", n.getPatientId().toString());
        map.add("e", n.getBody());
        return map;
    }

    @Test
    public void getPatientFromIdTest() {
        assertEquals(p1.toString(), service.getPatientFromID(p1.getPatientId()).toString());
        assertEquals(p2.toString(), service.getPatientFromID(p2.getPatientId()).toString());
    }

    @Test
    public void getPatientList() {
        List<Patient> returned = service.getPatientsList();
        assertTrue(returned.contains(p1));
        assertTrue(returned.contains(p2));
    }

    @Test
    public void createAndSavePatientTest() {
        service.createAndSavePatient(newP3);
        service.createAndSavePatient(newP4);
        List<Patient> db = patientClient.getPatients();
        boolean p3IsSavedCorrectly = false, p4IsSavedCorrectly = false;
        for(Patient p : db) {
            if(p.getFirstName().equals(p3.getFirstName()) &&
            p.getLastName().equals(p3.getLastName()) &&
            p.getBirthDate().toString().equals(p3.getBirthDate().toString()) &&
            p.getSex() == p3.getSex() &&
            p.getAddress().equals(p3.getAddress()) &&
            p.getPhone().equals(p3.getPhone())) {
                p3IsSavedCorrectly = true;
            }
            else if(p.getFirstName().equals(p4.getFirstName()) &&
            p.getLastName().equals(p4.getLastName()) &&
            p.getBirthDate().toString().equals(p4.getBirthDate().toString()) &&
            p.getSex() == p4.getSex() &&
            p.getAddress().equals(p4.getAddress()) &&
            p.getPhone().equals(p4.getPhone())) {
                p4IsSavedCorrectly = true;
            }
        }
        assertTrue(p3IsSavedCorrectly);
        assertTrue(p4IsSavedCorrectly);
    }

    @Test
    public void completePatientUpdateTest() {
        service.modifyPatient(p3, completeUpdateP3);
        assertEquals(updatedP3, patientClient.getPatientById(updatedP3.getPatientId()));
    }

    @Test
    public void partialPatientUpdateTest() {
        service.modifyPatient(p4, partialUpdateP4);
        assertEquals(updatedP4, patientClient.getPatientById(updatedP4.getPatientId()));
    }

    @Test
    public void getPatientFullHistoryByPatientIdTest() {
        List<Note> p1History = service.getPatientFullHistoryByPatientId(p1.getPatientId());
        List<Note> p2History = service.getPatientFullHistoryByPatientId(p2.getPatientId());
        assertTrue(p1History.contains(p1n1));
        assertTrue(p1History.contains(p1n2));
        assertTrue(p2History.contains(p2n1));
        assertTrue(p2History.contains(p2n2));
    }

    @Test
    public void findOneNoteFromPatientHistoryTest() {
        assertEquals(p1n1, service.findOneNoteFromPatientHistory(p1n1.getId()));
        assertEquals(p1n2, service.findOneNoteFromPatientHistory(p1n2.getId()));
        assertEquals(p2n1, service.findOneNoteFromPatientHistory(p2n1.getId()));
        assertEquals(p2n2, service.findOneNoteFromPatientHistory(p2n2.getId()));
    }

    @Test
    public void createAndSaveNoteToHistoryTest() {
        service.createAndSaveNoteToHistory(newP3N1);
        service.createAndSaveNoteToHistory(newP4N1);
        List<Note> db = historyClient.getHistoryByPatientId(null);
        boolean p3n1IsSavedCorrectly = false, p4n1IsSavedCorrectly = false;
        for(Note n : db) {
            if(n.getPatientId().toString().equals(p3.getPatientId().toString()) &&
            n.getBody().equals(p3n1.getBody())) {
                p3n1IsSavedCorrectly = true;
            }
            else if(n.getPatientId().toString().equals(p4.getPatientId().toString()) &&
            n.getBody().equals(p4n1.getBody())) {
                p4n1IsSavedCorrectly = true;
            }
        }
        assertTrue(p3n1IsSavedCorrectly);
        assertTrue(p4n1IsSavedCorrectly);
    }

    @Test
    public void modifyNoteTest() {
        service.modifyNote(p4n1, updateP4N1);
        assertEquals(updatedP4N1, historyClient.getOneNote(updatedP4N1.getId()));
    }
    
}