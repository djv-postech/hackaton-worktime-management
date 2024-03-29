package com.fiap.postech.hackatonworktimemanagement.domain.entities.registro;

import java.time.LocalDate;
import java.util.List;

public interface RegistroPontoFuncionarioRepository {

    RegistroPontoFuncionario registrarPonto(RegistroPontoFuncionario registroPontoFuncionario);

    List<RegistroPontoFuncionario> listarTodosOsRegistrosPorData(String matricula, LocalDate data);

    List<RegistroPontoFuncionario> listarTodosOsRegistrosPorMatricula(String matricula);

    List<RegistroPontoFuncionario> listarRegistrosMensal(String matricula, Integer mes, Integer ano);
}
