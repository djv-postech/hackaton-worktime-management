package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;

import java.time.LocalDate;
import java.util.List;

public class ListagemDeRegistro {

    private RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;

    public ListagemDeRegistro(RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository) {
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
    }

    public List<RegistroPontoFuncionario> listarTodosOsRegistrosDePontoPorData(String matricula, LocalDate data) {
        return registroPontoFuncionarioRepository.listarTodosOsRegistrosPorData(matricula, data);
    }

    public List<RegistroPontoFuncionario> listarRegistrosMensal(String matricula, Integer mes, Integer ano) {
        return registroPontoFuncionarioRepository.listarRegistrosMensal(matricula, mes, ano);
    }
}
