package com.dashdocapi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String path;
    private String message;
    private Calendar timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, Calendar timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorResponse(int status, String path, String message, Calendar timestamp) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorResponse(int status, String path, String message, Calendar timestamp, String cause) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = timestamp;
        this.cause = cause;
    }
}
