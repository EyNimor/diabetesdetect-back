package com.mediscreen.diabetesdetect.main.feign;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.diabetesdetect.main.config.FeignConfiguration;
import com.mediscreen.diabetesdetect.main.model.Patient;

@FeignClient(name = "PatientClient", url = "http://localhost:8082/patient/", configuration = FeignConfiguration.class)
public interface PatientClient {

    @GetMapping("/getPatientById")
    Patient getPatientById(@RequestParam(value = "uuid") UUID patientId);

    @GetMapping("/getPatients")
    List<Patient> getPatients();

    @PostMapping("/savePatient")
    void savePatient(@RequestBody Patient patient);
    
}
