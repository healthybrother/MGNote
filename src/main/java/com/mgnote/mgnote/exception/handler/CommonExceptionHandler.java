package com.mgnote.mgnote.exception.handler;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class NoteExceptionHandler.
 *
 * @author Danushka Jayamaha
 */
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionMessage resourceNotFoundException(final HttpServletRequest req, final EntityNotExistException ex) {
        return new ExceptionMessage(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage nullPointerExceptionHandler(NullPointerException ex){
        return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}