package com.mgnote.mgnote.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistException extends CommonException{
    public EntityAlreadyExistException(String message){
        super(HttpStatus.CONFLICT.value(), message);
    }
}
