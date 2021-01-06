package com.mgnote.mgnote.exception;

import org.springframework.http.HttpStatus;

public class HttpConnectionException extends CommonException{
    public HttpConnectionException(String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public HttpConnectionException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Http连接错误");
    }
}
