package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Cargo;

public record DadosCadastroFuncionario(String nome, Cargo cargo, String matricula, String senha) {
}
