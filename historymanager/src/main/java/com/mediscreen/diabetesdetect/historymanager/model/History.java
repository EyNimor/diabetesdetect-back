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
public class History {
    
    @Id
    String id;
    UUID patientId;
    String notes;

    public History(UUID patientId, String notes) {
        this.setId(UUID.randomUUID().toString());
        this.setPatientId(patientId);
        this.setNotes(notes);
    }

    public History(UUID id, UUID patientId, String notes) {
        this.setId(id.toString());
        this.setPatientId(patientId);
        this.setNotes(notes);
    }

}
