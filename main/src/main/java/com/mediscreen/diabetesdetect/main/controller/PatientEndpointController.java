package com.mediscreen.diabetesdetect.main.controller;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

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
import com.mediscreen.diabetesdetect.main.exception.PatientDoesNotExistException;
import com.mediscreen.diabetesdetect.main.model.Patient;
import com.mediscreen.diabetesdetect.main.service.MainService;

@RestController
@RequestMapping(value = "/patient")
@CrossOrigin
@ExcludeFromJacocoGeneratedReport
public class PatientEndpointController {

    @Autowired
    private MainService service;
    
    @GetMapping("/findById")
    @ResponseBody
    public Patient findPatientById(@RequestParam(value = "id") String patientId) {
        Patient patient = service.getPatientFromID(UUID.fromString(patientId));
        if(patient == null) {
            throw new PatientDoesNotExistException(patientId);
        } else {
            return patient;
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<Patient> findAllPatient() {
        return service.getPatientsList();
    }

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public String createPatient(@RequestBody MultiValueMap<String, String> newPatient) throws ParseException {
        UUID patientId = service.createAndSavePatient(newPatient);
        return "Profil de Patient créé avec succés ! ID attribué : " + patientId.toString();
    }

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public String updatePatient(@RequestParam MultiValueMap<String, String> newInfo) {
        Patient patientToModify = findPatientById(newInfo.get("id").get(0));
        service.modifyPatient(patientToModify, newInfo);
        return "Profil de Patient mis à jour avec succés !";
    }

}
