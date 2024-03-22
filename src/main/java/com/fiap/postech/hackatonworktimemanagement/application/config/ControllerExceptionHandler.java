package com.fiap.postech.hackatonworktimemanagement.application.config;

import com.fiap.postech.hackatonworktimemanagement.domain.exceptions.RegistrosInconsistentesException;
import com.fiap.postech.hackatonworktimemanagement.domain.exceptions.TipoDeRegistroInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ApiError> handlerRunTime(RuntimeException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TipoDeRegistroInvalidoException.class})
    protected ResponseEntity<ApiError> handlerTipoDeRegistroInvalido(TipoDeRegistroInvalidoException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RegistrosInconsistentesException.class})
    protected ResponseEntity<ApiError> handlerRegistrosInconsistentesException(RegistrosInconsistentesException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
