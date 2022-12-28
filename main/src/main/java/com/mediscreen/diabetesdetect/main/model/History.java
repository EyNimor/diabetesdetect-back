package com.mediscreen.diabetesdetect.main.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@NoArgsConstructor
public class History {
    
    UUID id;
    UUID patientId;
    String notes;

    public History(UUID patientId, String notes) {
        this.setId(UUID.randomUUID());
        this.setPatientId(patientId);
        this.setNotes(notes);
    }

    public History(String patientId, String notes) {
        this.setId(UUID.randomUUID());
        this.setPatientId(UUID.fromString(patientId));
        this.setNotes(notes);
    }

}
