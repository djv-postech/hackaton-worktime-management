package com.fiap.postech.hackatonworktimemanagement.domain.entities.registro;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroPonto {

    private LocalDate data;
    private LocalTime time;

    public RegistroPonto(LocalDate parse, LocalTime parse1) {
        this.data = parse;
        this.time = parse1;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getTime() {
        return time;
    }
}
