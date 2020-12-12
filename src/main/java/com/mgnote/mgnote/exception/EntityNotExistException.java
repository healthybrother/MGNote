package com.mgnote.mgnote.exception;

import org.springframework.http.HttpStatus;

public class EntityNotExistException extends CommonException{

    public EntityNotExistException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
