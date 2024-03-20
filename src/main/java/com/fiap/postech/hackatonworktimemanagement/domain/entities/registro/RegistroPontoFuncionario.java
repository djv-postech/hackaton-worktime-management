package com.fiap.postech.hackatonworktimemanagement.domain.entities.registro;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegistroPontoFuncionario {

    public String matricula;
    public LocalDate data;
    public LocalTime hora;
    public TipoBatida tipoBatida;

    public RegistroPontoFuncionario() {
    }

    public RegistroPontoFuncionario(String matricula, LocalDate data, LocalTime hora, TipoBatida tipoBatida) {
        this.matricula = matricula;
        this.data = data;
        this.hora = hora;
        this.tipoBatida = tipoBatida;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setTipoBatida(TipoBatida tipoBatida) {
        this.tipoBatida = tipoBatida;
    }

    public TipoBatida getTipoBatida() {
        return tipoBatida;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(formatter);
    }

    public String getHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return this.hora.format(formatter);
    }
}
