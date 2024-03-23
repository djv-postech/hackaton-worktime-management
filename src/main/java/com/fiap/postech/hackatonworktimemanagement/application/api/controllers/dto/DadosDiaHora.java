package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

public record DadosDiaHora(String hora, TipoRegistro tipoRegistro) {

    public DadosDiaHora(RegistroPontoFuncionario registroPontoFuncionario){
        this(registroPontoFuncionario.getHoraFormatada(), registroPontoFuncionario.getTipoBatida());
    }
}
