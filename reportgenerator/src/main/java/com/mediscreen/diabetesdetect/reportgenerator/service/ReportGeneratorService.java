package com.mediscreen.diabetesdetect.reportgenerator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetesdetect.reportgenerator.constant.ReportStatus;
import com.mediscreen.diabetesdetect.reportgenerator.constant.Sex;
import com.mediscreen.diabetesdetect.reportgenerator.feign.HistoryClient;
import com.mediscreen.diabetesdetect.reportgenerator.model.Note;

@Service
public class ReportGeneratorService {

    private static Logger log = LoggerFactory.getLogger(ReportGeneratorService.class);
    
    @Autowired
    private HistoryClient historyClient;

    private HashMap<String, Boolean> triggersDetectedMap;

    public ReportGeneratorService() {
        initTriggersMap();
    }

    public ReportGeneratorService(HistoryClient historyClient) {
        this.historyClient = historyClient;
        initTriggersMap();
    }

    private void initTriggersMap() {
        triggersDetectedMap = new HashMap<>();
        triggersDetectedMap.put("hémoglobine a1c", false);
        triggersDetectedMap.put("microalbumine", false);
        triggersDetectedMap.put("taille", false);
        triggersDetectedMap.put("poids", false);
        triggersDetectedMap.put("fumeur", false);
        triggersDetectedMap.put("anormal", false);
        triggersDetectedMap.put("cholestérol", false);
        triggersDetectedMap.put("vertige", false);
        triggersDetectedMap.put("rechute", false);
        triggersDetectedMap.put("réaction", false);
        triggersDetectedMap.put("anticorps", false);
    }

    public ReportStatus generateReport(UUID patientId, int patientAge, Sex patientSex) {
        List<Note> historyToAnalyze = historyClient.getHistoryByPatientId(patientId);

        int triggersDetected = 0;
        for(Note n : historyToAnalyze) {
            String body = n.getBody().toLowerCase(Locale.ROOT);
            if(body.contains("hémoglobine a1c") && !triggersDetectedMap.get("hémoglobine a1c")) {
                ++triggersDetected;
                triggersDetectedMap.put("hémoglobine a1c", true);
            }
            if(body.contains("microalbumine") && !triggersDetectedMap.get("microalbumine")) {
                ++triggersDetected;
                triggersDetectedMap.put("microalbumine", true);
            }
            if(body.contains("taille") && !triggersDetectedMap.get("taille")) {
                ++triggersDetected;
                triggersDetectedMap.put("taille", true);
            }
            if(body.contains("poids") && !triggersDetectedMap.get("poids")) {
                ++triggersDetected;
                triggersDetectedMap.put("poids", true);
            }
            if((body.contains("fumeur") || body.contains("fume") || body.contains("fumer")) && !triggersDetectedMap.get("fumeur")) {
                ++triggersDetected;
                triggersDetectedMap.put("fumeur", true);
            }
            if(body.contains("anormal") && !triggersDetectedMap.get("anormal")) {
                ++triggersDetected;
                triggersDetectedMap.put("anormal", true);
            }
            if(body.contains("cholestérol") && !triggersDetectedMap.get("cholestérol")) {
                ++triggersDetected;
                triggersDetectedMap.put("cholestérol", true);
            }
            if(body.contains("vertige") && !triggersDetectedMap.get("vertige")) {
                ++triggersDetected;
                triggersDetectedMap.put("vertige", true);
            }
            if(body.contains("rechute") && !triggersDetectedMap.get("rechute")) {
                ++triggersDetected;
                triggersDetectedMap.put("rechute", true);
            }
            if(body.contains("réaction") && !triggersDetectedMap.get("réaction")) {
                ++triggersDetected;
                triggersDetectedMap.put("réaction", true);
            }
            if(body.contains("anticorps") && !triggersDetectedMap.get("anticorps")) {
                ++triggersDetected;
                triggersDetectedMap.put("anticorps", true);
            }
            if(!triggersDetectedMap.containsValue(false)) {
                break;
            }
        }
        initTriggersMap();
        log.info("Triggers Detected : " + triggersDetected);
        if((patientAge > 30 && (triggersDetected > 1 && triggersDetected < 6))) {
            return ReportStatus.BORDERLINE;
        }
        else if((patientAge < 30 && patientSex == Sex.MEN && (triggersDetected > 2 && triggersDetected < 5)) ||
        (patientAge < 30 && patientSex == Sex.WOMEN && (triggersDetected > 3 && triggersDetected < 7)) || 
        (patientAge > 30 && (triggersDetected > 5 && triggersDetected < 8))) {
            return ReportStatus.IN_DANGER;
        }
        else if((patientAge < 30 && patientSex == Sex.MEN && triggersDetected > 4) ||
        (patientAge < 30 && patientSex == Sex.WOMEN && triggersDetected > 6) || 
        (patientAge > 30 && triggersDetected > 7)) {
            return ReportStatus.EARLY_ONSET;
        }
        else {
            return ReportStatus.NONE;
        }
    }

}
