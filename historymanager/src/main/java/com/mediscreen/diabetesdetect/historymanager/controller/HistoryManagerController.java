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

import com.mediscreen.diabetesdetect.historymanager.model.Note;
import com.mediscreen.diabetesdetect.historymanager.service.HistoryManagerService;

@RestController
@RequestMapping(value = "/history")
public class HistoryManagerController {
    
    @Autowired
    private HistoryManagerService service;

    @GetMapping("/getHistoryByPatientId")
    public List<Note> getPatientById(@RequestParam(value = "uuid") UUID patientId) {
        List<Note> historic = service.getHistoryByPatientId(patientId);
        return historic;
    }

    @GetMapping("/getOneNote")
    public Note getOneNote(@RequestParam(value = "uuid") UUID noteId) {
        Note history = service.getOneNoteFromNoteId(noteId.toString());
        return history;
    }

    @PostMapping("/saveHistory")
    public void saveHistory(@RequestBody Note newHistory) {
        service.saveHistory(newHistory);
    }

    @PutMapping("/updateHistory")
    public void updateHistory(@RequestBody Note historyToUpdate) {
        service.updateHistory(historyToUpdate);
    }

}
