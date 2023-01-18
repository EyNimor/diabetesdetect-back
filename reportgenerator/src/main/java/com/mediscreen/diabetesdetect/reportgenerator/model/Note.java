package com.mediscreen.diabetesdetect.reportgenerator.model;

import java.util.UUID;

import com.mediscreen.diabetesdetect.reportgenerator.annotation.ExcludeFromJacocoGeneratedReport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@NoArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class Note {
    
    UUID id;
    UUID patientId;
    String body;

    public Note(UUID patientId, String body) {
        this.setId(UUID.randomUUID());
        this.setPatientId(patientId);
        this.setBody(body);
    }

    public Note(String patientId, String body) {
        this.setId(UUID.randomUUID());
        this.setPatientId(UUID.fromString(patientId));
        this.setBody(body);
    }

}
