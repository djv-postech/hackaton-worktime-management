package com.fiap.postech.hackatonworktimemanagement.domain.registro.ports;


import com.fiap.postech.hackatonworktimemanagement.infra.adapter.entity.RegistroPontoFuncionarioEntity;

public interface RegistroPontoFuncionarioRepositoryPort {

  void registarBatida(RegistroPontoFuncionarioEntity registroPontoFuncionarioEntity);


}
