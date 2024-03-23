package com.fiap.postech.hackatonworktimemanagement.domain.exceptions;

public class RegistrosInconsistentesException extends RuntimeException {
    public RegistrosInconsistentesException(String message) {
        super(message);
    }
}