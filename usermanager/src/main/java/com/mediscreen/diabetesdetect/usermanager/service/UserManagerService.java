package com.mediscreen.diabetesdetect.usermanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetesdetect.usermanager.dao.UserRepository;
import com.mediscreen.diabetesdetect.usermanager.model.Patient;

@Service
public class UserManagerService {

    @Autowired
    private UserRepository dao;

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
