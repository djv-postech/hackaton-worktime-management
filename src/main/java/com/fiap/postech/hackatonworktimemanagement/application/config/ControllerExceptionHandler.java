package com.fiap.postech.hackatonworktimemanagement.application.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(value = {ProdutoNotFoundException.class})
//    protected ResponseEntity<com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError> handlerEntityNotFound(ProdutoNotFoundException ex) {
//        com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError error = new com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError(ex.getMessage(),HttpStatus.NOT_FOUND.value());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(value = {IllegalArgumentException.class})
//    protected ResponseEntity<com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError> handlerMercadoPagoQRCodeException(IllegalArgumentException ex) {
//        com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError error = new com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

}
