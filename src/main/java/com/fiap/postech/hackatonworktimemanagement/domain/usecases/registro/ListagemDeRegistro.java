package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;

import java.util.List;

public class ListagemDeRegistro {

    private RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;

    public ListagemDeRegistro(RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository) {
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
    }

    public List<RegistroPontoFuncionario> listarTodosOsRegistros(String matricula) {
        return registroPontoFuncionarioRepository.listarTodosOsRegistros(matricula);
    }
}
