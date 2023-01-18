package com.mediscreen.diabetesdetect.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mediscreen.diabetesdetect.main.annotation.ExcludeFromJacocoGeneratedReport;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@ExcludeFromJacocoGeneratedReport
public class PatientDoesNotExistException extends RuntimeException {

    public PatientDoesNotExistException(String patientId) {
        super("The Patient with ID " + patientId + " does not exist or cannot be find");
    }
    
}
