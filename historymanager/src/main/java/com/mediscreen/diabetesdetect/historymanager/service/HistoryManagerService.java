package com.mediscreen.diabetesdetect.historymanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetesdetect.historymanager.dao.HistoryRepository;
import com.mediscreen.diabetesdetect.historymanager.model.Note;

@Service
public class HistoryManagerService {

    @Autowired
	private HistoryRepository dao;

    public List<Note> getHistoryByPatientId(UUID patientId) {
        return dao.findByPatientId(patientId);
    }

    public void saveHistory(Note newHistory) {
        dao.insert(newHistory);
    }

    public Note getOneNoteFromNoteId(String noteId) {
        return dao.findById(noteId).get();
    }

    public void updateHistory(Note historyToUpdate) {
        dao.deleteById(historyToUpdate.getId());
        dao.insert(historyToUpdate);
    }
    
}
