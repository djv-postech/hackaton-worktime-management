package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

public class DadosSolicitacaoEspelho {

    private String matricula;

    private DadosMes mes;

    @JsonProperty("ano")
    @Pattern(regexp = "^\\d{4}$", message = "Ano deve ser no formato aaaa")
    private Integer ano;

    public DadosSolicitacaoEspelho(String matricula, DadosMes mes, Integer ano) {
        this.matricula = matricula;
        this.mes = mes;
        this.ano = ano;
    }

    public String getMatricula() {
        return matricula;
    }

    public DadosMes getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }
}
