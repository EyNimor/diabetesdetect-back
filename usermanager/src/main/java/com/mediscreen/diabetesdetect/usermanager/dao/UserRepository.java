package com.mediscreen.diabetesdetect.usermanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediscreen.diabetesdetect.usermanager.model.Patient;

public interface UserRepository extends JpaRepository<Patient, String> {

}
