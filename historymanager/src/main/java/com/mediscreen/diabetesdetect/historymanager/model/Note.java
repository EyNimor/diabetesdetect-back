package com.mediscreen.diabetesdetect.historymanager.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@NoArgsConstructor
@Document(collection = "history")
public class Note {
    
    @Id
    String id;
    UUID patientId;
    String body;

    public Note(UUID patientId, String body) {
        this.setId(UUID.randomUUID().toString());
        this.setPatientId(patientId);
        this.setBody(body);
    }

    public Note(UUID id, UUID patientId, String body) {
        this.setId(id.toString());
        this.setPatientId(patientId);
        this.setBody(body);
    }

}
