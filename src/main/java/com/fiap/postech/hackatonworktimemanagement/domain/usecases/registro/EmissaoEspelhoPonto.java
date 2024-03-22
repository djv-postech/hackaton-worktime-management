package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records.Mes;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;

public class EmissaoEspelhoPonto {

    private RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;

    public EmissaoEspelhoPonto(RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository) {
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
    }

    public void solicitarEspelhoDePontoMensal(String matricula, Mes mes) {
        registroPontoFuncionarioRepository.listarTodosOsRegistrosPorMatricula(matricula);
    }
}
