package com.mediscreen.diabetesdetect.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HistoryNoteDoesNotExistException extends RuntimeException {
    
    public HistoryNoteDoesNotExistException(String noteId) {
        super("The Note with ID " + noteId + " does not exist or cannot be find");
    }

}
