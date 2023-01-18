package com.mediscreen.diabetesdetect.reportgenerator.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.diabetesdetect.reportgenerator.annotation.ExcludeFromJacocoGeneratedReport;
import com.mediscreen.diabetesdetect.reportgenerator.constant.ReportStatus;
import com.mediscreen.diabetesdetect.reportgenerator.constant.Sex;
import com.mediscreen.diabetesdetect.reportgenerator.service.ReportGeneratorService;

@RestController
@RequestMapping(value = "/report")
@CrossOrigin
@ExcludeFromJacocoGeneratedReport
public class ReportGeneratorController {

    @Autowired
    private ReportGeneratorService service;
    
    @GetMapping("/generate")
    public ReportStatus generateReport(@RequestParam(value = "id") UUID patientId, @RequestParam(value = "age") int patientAge, @RequestParam(value = "sex") String patientSex) {
        return service.generateReport(patientId, patientAge, Sex.valueOf(patientSex));
    }

}
