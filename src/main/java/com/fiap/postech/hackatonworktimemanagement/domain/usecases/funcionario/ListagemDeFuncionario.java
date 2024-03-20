package com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;

import java.util.List;

public class ListagemDeFuncionario {

    private FuncionarioRepository funcionarioRepository;

    public ListagemDeFuncionario(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodosOsFuncionarios() {
        return funcionarioRepository.todosOsFuncionarios();
    }
}
