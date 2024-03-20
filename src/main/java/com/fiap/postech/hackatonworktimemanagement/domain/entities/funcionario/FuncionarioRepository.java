package com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario;

import java.util.List;

public interface FuncionarioRepository {

    Funcionario cadastrarFuncionario(Funcionario funcionario);

    List<Funcionario> todosOsFuncionarios();

    Funcionario buscarFuncionarioPorMatricula(String matricula);
}
