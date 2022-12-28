package com.mediscreen.diabetesdetect.historymanager.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.diabetesdetect.historymanager.model.History;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {

    public List<History> findByPatientId(UUID patientId);
    
}
