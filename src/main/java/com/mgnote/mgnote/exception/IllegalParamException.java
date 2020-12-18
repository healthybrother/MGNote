package com.mgnote.mgnote.exception;

import org.springframework.http.HttpStatus;

public class IllegalParamException extends CommonException{
    public IllegalParamException(String message){
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
