package com.fiap.postech.hackatonworktimemanagement.application.api.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoBatida;

public record DadosRegistroPonto(String matricula, String data, String hora, TipoBatida tipoBatida) {

    public DadosRegistroPonto(RegistroPontoFuncionario registroPontoFuncionario) {
        this(registroPontoFuncionario.getMatricula(), registroPontoFuncionario.getDataFormatada(), registroPontoFuncionario.getHoraFormatada(),
                registroPontoFuncionario.tipoBatida);
    }
}
