package com.mediscreen.diabetesdetect.historymanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.diabetesdetect.historymanager.model.History;
import com.mediscreen.diabetesdetect.historymanager.service.HistoryManagerService;

@RestController
@RequestMapping(value = "/history")
public class HistoryManagerController {
    
    @Autowired
    private HistoryManagerService service;

    @GetMapping("/getHistoryByPatientId")
    public List<History> getPatientById(@RequestParam(value = "uuid") UUID patientId) {
        List<History> historic = service.getHistoryByPatientId(patientId);
        return historic;
    }

    @GetMapping("/getOneNote")
    public History getOneNote(@RequestParam(value = "uuid") UUID noteId) {
        History history = service.getOneNoteFromNoteId(noteId.toString());
        return history;
    }

    @PostMapping("/saveHistory")
    public void saveHistory(@RequestBody History newHistory) {
        service.saveHistory(newHistory);
    }

    @PutMapping("/updateHistory")
    public void updateHistory(@RequestBody History historyToUpdate) {
        service.updateHistory(historyToUpdate);
    }

}
