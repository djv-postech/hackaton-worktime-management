package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;

public record DadosFuncionario(String nome, String matricula, String cargo) {

    public DadosFuncionario(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getMatricula(), funcionario.getCargo().toString());
    }
}
