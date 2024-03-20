package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosCadastroPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistrarPontoFuncionario {

    private RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;
    private FuncionarioRepository funcionarioRepository;

    public RegistrarPontoFuncionario(RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository,
    FuncionarioRepository funcionarioRepository) {
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public RegistroPontoFuncionario registrar(DadosCadastroPonto dadosCadastroPonto) {

    Funcionario funcionario = funcionarioRepository
            .buscarFuncionarioPorMatricula(dadosCadastroPonto.matricula());

        RegistroPontoFuncionario registroPonto = new RegistroPontoFuncionario();
        registroPonto.setMatricula(funcionario.getMatricula());
        registroPonto.setData(LocalDate.now());
        registroPonto.setHora(LocalTime.now());
        registroPonto.setTipoBatida(dadosCadastroPonto.tipoRegistro());

    return registroPontoFuncionarioRepository.registrarPonto(registroPonto);
    }
}
