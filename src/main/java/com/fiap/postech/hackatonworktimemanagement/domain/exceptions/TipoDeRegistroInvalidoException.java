package com.fiap.postech.hackatonworktimemanagement.domain.exceptions;

public class TipoDeRegistroInvalidoException extends RuntimeException {
    public TipoDeRegistroInvalidoException(String message) {
        super(message);
    }
}