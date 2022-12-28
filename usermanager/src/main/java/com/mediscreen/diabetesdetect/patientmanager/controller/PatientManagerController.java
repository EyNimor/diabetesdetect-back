package com.mediscreen.diabetesdetect.patientmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.diabetesdetect.patientmanager.model.Patient;
import com.mediscreen.diabetesdetect.patientmanager.service.PatientManagerService;

@RestController
@RequestMapping(value = "/patient")
public class PatientManagerController {

    @Autowired
    private PatientManagerService service;

    @GetMapping("/getPatientById")
    public Patient getPatientById(@RequestParam(value = "uuid") UUID patientId) {
        Patient user = service.getPatientById(patientId);
        return user;
    }

    @GetMapping("/getPatients")
    public List<Patient> getPatients() {
        List<Patient> usersList = service.getPatientsList();
        return usersList;
    }

    @PostMapping("/savePatient")
    public void savePatient(@RequestBody Patient patientToSave) {
        service.savePatient(patientToSave);
    }
    
}
