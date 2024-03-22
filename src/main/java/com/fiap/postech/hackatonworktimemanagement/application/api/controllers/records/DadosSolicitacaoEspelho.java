package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class DadosSolicitacaoEspelho {

    private String matricula;

    //TODO nada funfou - a proposito, minha IDE tem algum problema que as mudancas nao refletem (continua..)
    //TODO estou tendo que apagar a pasta target e dar mvn clean - vou investigar o por que
    @DateTimeFormat(pattern = "MM/yyyy")
    @JsonFormat(pattern = "MM/yyyy")
    @Schema(example = "03/2024")
    private LocalDate data;

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getData() {
        return data;
    }
}
