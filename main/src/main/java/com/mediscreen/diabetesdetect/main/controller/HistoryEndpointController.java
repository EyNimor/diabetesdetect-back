package com.mediscreen.diabetesdetect.main.controller;

import java.util.List;
import java.util.UUID;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.diabetesdetect.main.annotation.ExcludeFromJacocoGeneratedReport;
import com.mediscreen.diabetesdetect.main.exception.HistoryNoteDoesNotExistException;
import com.mediscreen.diabetesdetect.main.exception.PatientDoesNotExistException;
import com.mediscreen.diabetesdetect.main.model.History;
import com.mediscreen.diabetesdetect.main.service.MainService;

@RestController
@RequestMapping(value = "/history")
@CrossOrigin
@ExcludeFromJacocoGeneratedReport
public class HistoryEndpointController {

    @Autowired
    private MainService service;

    @GetMapping("/findByPatientId")
    @ResponseBody
    public List<History> findPatientHistory(@RequestParam(value = "patId") String stringPatientId) {
        UUID patientId = UUID.fromString(stringPatientId);
        if(service.getPatientFromID(patientId) == null) {
            throw new PatientDoesNotExistException(stringPatientId);
        } else {
            List<History> patientHistory = service.getPatientFullHistoryByPatientId(patientId);
            return patientHistory;
        }
    }

    @PostMapping(path = "/addNote", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public String addNote(@RequestBody MultiValueMap<String, String> newNote) {
        service.createAndSaveHistory(newNote);
        return "Note ajouté avec succés !";
    }

    @PutMapping(path = "/updateNote", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public String updateNote(@RequestParam MultiValueMap<String, String> newInfo) {
        History historyToModify = service.findOneNoteFromPatientHistory(UUID.fromString(newInfo.get("id").get(0)));
        if(historyToModify == null) {
            throw new HistoryNoteDoesNotExistException(newInfo.get("id").get(0));
        } else {
            service.modifyHistory(historyToModify, newInfo);
            return "Note modifié avec succés !";
        }
    }
    
}
