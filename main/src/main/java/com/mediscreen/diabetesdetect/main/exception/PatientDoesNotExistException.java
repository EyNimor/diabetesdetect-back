package com.mediscreen.diabetesdetect.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PatientDoesNotExistException extends RuntimeException {

    public PatientDoesNotExistException(String patientId) {
        super("The Patient with ID " + patientId + " does not exist or cannot be find");
    }
    
}
