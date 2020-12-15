package com.mgnote.mgnote.exception;

import org.springframework.http.HttpStatus;

public class PermissionException extends CommonException{

    public PermissionException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }

}
