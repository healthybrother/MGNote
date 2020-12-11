package com.mgnote.mgnote.exception;

public class EntityNotExistException extends Exception{

    public EntityNotExistException(String message) {
        super(message);
    }

    public EntityNotExistException(){}
}
