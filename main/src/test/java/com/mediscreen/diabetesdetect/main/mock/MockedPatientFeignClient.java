package com.mediscreen.diabetesdetect.main.mock;

import java.util.List;
import java.util.UUID;

import com.mediscreen.diabetesdetect.main.feign.PatientClient;
import com.mediscreen.diabetesdetect.main.model.Patient;

public class MockedPatientFeignClient implements PatientClient {

    List<Patient> patientList;

    public MockedPatientFeignClient(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @Override
    public Patient getPatientById(UUID patientId) {
        Patient patient = null;
        for(Patient p : patientList) {
            if(p.getPatientId() == patientId) {
                patient = p;
                break;
            }
        }
        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        return patientList;
    }

    @Override
    public void savePatient(Patient patient) {
        for(Patient p : patientList) {
            if(p.getPatientId() == patient.getPatientId()) {
                patientList.remove(p);
                break;
            }
        }
        patientList.add(patient);
    }
    
}
