package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records.DadosCadastroPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.exceptions.TipoDeRegistroInvalidoException;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import static java.lang.String.format;

public class RegistrarPontoFuncionario {

    private RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;
    private FuncionarioRepository funcionarioRepository;

    public RegistrarPontoFuncionario(RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository,
    FuncionarioRepository funcionarioRepository) {
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public RegistroPontoFuncionario registrar(DadosCadastroPonto dadosCadastroPonto) {

        validarRegistro(dadosCadastroPonto);

        RegistroPontoFuncionario registroPonto = new RegistroPontoFuncionario();
        registroPonto.setMatricula(dadosCadastroPonto.matricula());
        registroPonto.setData(LocalDate.now());
        registroPonto.setHora(LocalTime.now());
        registroPonto.setTipoBatida(dadosCadastroPonto.tipoRegistro());

    return registroPontoFuncionarioRepository.registrarPonto(registroPonto);
    }

    private void validarRegistro(DadosCadastroPonto dadosCadastroPonto){

         funcionarioRepository
                .buscarFuncionarioPorMatricula(dadosCadastroPonto.matricula());

        var registros =  registroPontoFuncionarioRepository.listarTodosOsRegistrosPorData(dadosCadastroPonto.matricula(), LocalDate.now());

       registros.stream()
                .max(Comparator.comparing(RegistroPontoFuncionario::getData)
                        .thenComparing(RegistroPontoFuncionario::getHora))
                 .filter(registro -> registro.tipoRegistro.equals(dadosCadastroPonto.tipoRegistro())).ifPresent(s -> {
            throw new TipoDeRegistroInvalidoException(format("Tipo de registro %s inválido, já foi registrado previamente.", dadosCadastroPonto.tipoRegistro()));
        });;

    }
}
