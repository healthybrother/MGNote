package com.mgnote.mgnote.exception.handler;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.dto.ResponseValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class NoteExceptionHandler.
 *
 * @author Danushka Jayamaha
 */
@ControllerAdvice
public class NoteExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseValue<?> resourceNotFoundException(final HttpServletRequest req, final EntityNotExistException ex) {
        return ResponseValue.fail("信息不存在");
    }
}