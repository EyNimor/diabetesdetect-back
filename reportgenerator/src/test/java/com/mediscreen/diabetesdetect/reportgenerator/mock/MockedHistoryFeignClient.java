package com.mediscreen.diabetesdetect.reportgenerator.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mediscreen.diabetesdetect.reportgenerator.feign.HistoryClient;
import com.mediscreen.diabetesdetect.reportgenerator.model.Note;

public class MockedHistoryFeignClient implements HistoryClient {

    List<Note> history;

    public MockedHistoryFeignClient(List<Note> history) {
        this.history = history;
    }

    @Override
    public List<Note> getHistoryByPatientId(UUID patientId) {
        List<Note> patientHistory = new ArrayList<>();
        if(patientId == null) {
            return history;
        }
        else {
            for(Note n : history) {
                if(n.getPatientId() == patientId) {
                    patientHistory.add(n);
                }
            }
            return patientHistory;
        }
    }

    @Override
    public Note getOneNote(UUID noteId) {
        Note note = null;
        for(Note n : history) {
            if(n.getId() == noteId) {
                note = n;
                break;
            }
        }
        return note;
    }

    @Override
    public void saveHistory(Note newHistory) {
        history.add(newHistory);
    }

    @Override
    public void updateHistory(Note historyToUpdate) {
        for(Note n : history) {
            if(n.getId() == historyToUpdate.getId()) {
                history.remove(n);
                break;
            }
        }
        history.add(historyToUpdate);
    }
    
}
