package com.fiap.postech.hackatonworktimemanagement.domain.entities.registro;

import java.util.List;

public interface RegistroPontoFuncionarioRepository {

    RegistroPontoFuncionario registrarPonto(RegistroPontoFuncionario registroPontoFuncionario);

    List<RegistroPontoFuncionario> listarTodosOsRegistros(String matricula);
}
