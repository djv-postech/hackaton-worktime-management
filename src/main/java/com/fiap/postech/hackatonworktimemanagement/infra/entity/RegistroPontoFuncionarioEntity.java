package com.fiap.postech.hackatonworktimemanagement.infra.entity;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoBatida;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class RegistroPontoFuncionarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricula;
    private LocalDate data;
    private LocalTime hora;
    private TipoBatida tipoBatida;

    public RegistroPontoFuncionarioEntity() {
    }

    public RegistroPontoFuncionarioEntity(String matricula, LocalDate data, LocalTime hora, TipoBatida tipoBatida) {
        this.matricula = matricula;
        this.data = data;
        this.hora = hora;
        this.tipoBatida = tipoBatida;
    }

    public static RegistroPontoFuncionarioEntity from(RegistroPontoFuncionario registroPontoFuncionario) {
        return new RegistroPontoFuncionarioEntity(registroPontoFuncionario.getMatricula(), registroPontoFuncionario.getData(),
                registroPontoFuncionario.getHora(), registroPontoFuncionario.getTipoBatida());
    }

    public Integer getId() {
        return id;
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

    public TipoBatida getTipoBatida() {
        return tipoBatida;
    }
}
