package com.mediscreen.diabetesdetect.patientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediscreen.diabetesdetect.patientmanager.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
