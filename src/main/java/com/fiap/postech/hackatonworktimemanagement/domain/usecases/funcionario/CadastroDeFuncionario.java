package com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;

public class CadastroDeFuncionario {

    private final FuncionarioRepository funcionarioRepository;

    public CadastroDeFuncionario(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.cadastrarFuncionario(funcionario);
    }
}

