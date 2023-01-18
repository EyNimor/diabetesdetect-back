package com.mediscreen.diabetesdetect.historymanager.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.diabetesdetect.historymanager.model.Note;

@Repository
public interface HistoryRepository extends MongoRepository<Note, String> {

    public List<Note> findByPatientId(UUID patientId);
    
}
