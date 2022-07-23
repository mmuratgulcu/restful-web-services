package com.muratgulcu.rest.webservices.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String messages;
    private String details;

    public ExceptionResponse(LocalDateTime timestamp, String messages, String details) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessages() {
        return messages;
    }

    public String getDetails() {
        return details;
    }
}
