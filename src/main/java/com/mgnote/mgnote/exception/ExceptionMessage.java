package com.mgnote.mgnote.exception;

import java.util.Map;

public class ExceptionMessage {
    private int code;
    private String message;
    private Map<String, String> errors;

    public ExceptionMessage(int code, String message, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionMessage(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ExceptionMessage() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
