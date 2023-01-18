package com.mediscreen.diabetesdetect.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mediscreen.diabetesdetect.main.constant.ReportStatus;
import com.mediscreen.diabetesdetect.main.constant.Sex;
import com.mediscreen.diabetesdetect.main.feign.HistoryClient;
import com.mediscreen.diabetesdetect.main.feign.PatientClient;
import com.mediscreen.diabetesdetect.main.feign.ReportClient;
import com.mediscreen.diabetesdetect.main.model.Note;
import com.mediscreen.diabetesdetect.main.model.Patient;

import feign.FeignException;

@Service
public class MainService {
    
    @Autowired
    private PatientClient patientClient;

    @Autowired
    private HistoryClient historyClient;

    @Autowired
    private ReportClient reportClient;

    // Test specific constructor, used to inject mocked Feign Client
    public MainService(PatientClient patientClient, HistoryClient historyClient) {
        this.patientClient = patientClient;
        this.historyClient = historyClient;
    }

    public Patient getPatientFromID(UUID patientId) {
        Patient patient;
        try {
            patient = patientClient.getPatientById(patientId);
        } catch(FeignException e) {
            e.fillInStackTrace();
            patient = null;
        }
        return patient;
    }

    public List<Patient> getPatientsList() {
        return patientClient.getPatients();
    }

    public UUID createAndSavePatient(MultiValueMap<String, String> newPatient) {
        LocalDate dob = LocalDate.parse(newPatient.get("dob").get(0));
        Patient patient = new Patient(newPatient.get("family").get(0), newPatient.get("given").get(0), dob, newPatient.get("sex").get(0), newPatient.get("address").get(0), newPatient.get("phone").get(0));
        patientClient.savePatient(patient);
        return patient.getPatientId();
    }

    public void modifyPatient(Patient patientToModify, MultiValueMap<String, String> newInfo) {
        if(newInfo.containsKey("family") && newInfo.get("family").get(0) != "") {
            patientToModify.setLastName(newInfo.get("family").get(0));
        }
        if(newInfo.containsKey("given") && newInfo.get("given").get(0) != "") {
            patientToModify.setFirstName(newInfo.get("given").get(0));
        }
        if(newInfo.containsKey("dob") && newInfo.get("dob").get(0) != "") {
            patientToModify.setBirthDate(LocalDate.parse(newInfo.get("dob").get(0)));
        }
        if(newInfo.containsKey("sex") && newInfo.get("sex").get(0) != "") {
            patientToModify.setSexFromString(newInfo.get("sex").get(0));
        }
        if(newInfo.containsKey("address") && newInfo.get("address").get(0) != "") {
            patientToModify.setAddress(newInfo.get("address").get(0));
        }
        if(newInfo.containsKey("phone") && newInfo.get("phone").get(0) != "") {
            patientToModify.setAddress(newInfo.get("phone").get(0));
        }
        patientClient.savePatient(patientToModify);
    }

    public List<Note> getPatientFullHistoryByPatientId(UUID patientId) {
        return historyClient.getHistoryByPatientId(patientId);
    }

    public Note findOneNoteFromPatientHistory(UUID noteId) {
        Note history;
        try {
            history = historyClient.getOneNote(noteId);
        } catch(FeignException e) {
            e.fillInStackTrace();
            history = null;
        }
        return history;
    }

    public void createAndSaveNoteToHistory(MultiValueMap<String, String> newNote) {
        Note history = new Note(newNote.get("patId").get(0), newNote.get("e").get(0));
        historyClient.saveHistory(history);
    }

    public void modifyNote(Note historyToModify, MultiValueMap<String, String> newInfo) {
        historyToModify.setBody(newInfo.get("e").get(0));
        historyClient.updateHistory(historyToModify);
    }

    public ReportStatus generateReport(Patient patient) {
        return reportClient.generateReport(patient.getPatientId(), calculateAge(patient.getBirthDate()), patient.getSex().toString());
    }

    public int calculateAge(LocalDate birthDate) {
        int age = LocalDate.now().getYear() - birthDate.getYear();
        if(LocalDate.now().getMonthValue() < birthDate.getMonthValue() ||
        (LocalDate.now().getMonthValue() == birthDate.getMonthValue() && LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth())) {
            --age;
        }
        return age;
    }

}
