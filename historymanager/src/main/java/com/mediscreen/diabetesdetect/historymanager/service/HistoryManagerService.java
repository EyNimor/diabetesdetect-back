package com.mediscreen.diabetesdetect.historymanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetesdetect.historymanager.dao.HistoryRepository;
import com.mediscreen.diabetesdetect.historymanager.model.History;

@Service
public class HistoryManagerService {

    @Autowired
	private HistoryRepository dao;

    public List<History> getHistoryByPatientId(UUID patientId) {
        return dao.findByPatientId(patientId);
    }

    public void saveHistory(History newHistory) {
        dao.insert(newHistory);
    }

    public History getOneNoteFromNoteId(String noteId) {
        return dao.findById(noteId).get();
    }

    public void updateHistory(History historyToUpdate) {
        dao.deleteById(historyToUpdate.getId());
        dao.insert(historyToUpdate);
    }
    
}
