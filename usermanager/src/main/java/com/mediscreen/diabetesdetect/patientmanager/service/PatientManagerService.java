package com.mediscreen.diabetesdetect.patientmanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetesdetect.patientmanager.dao.PatientRepository;
import com.mediscreen.diabetesdetect.patientmanager.model.Patient;

@Service
public class PatientManagerService {

    @Autowired
    private PatientRepository dao;

    public Patient getPatientById(UUID patientId) {
        return dao.findById(patientId.toString()).get();
    }

    public List<Patient> getPatientsList() {
        return dao.findAll();
    }

    public void savePatient(Patient patientToSave) {
        dao.saveAndFlush(patientToSave);
    }
    
}
