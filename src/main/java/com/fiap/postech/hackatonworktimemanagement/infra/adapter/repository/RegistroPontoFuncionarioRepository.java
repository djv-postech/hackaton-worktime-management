package com.fiap.postech.hackatonworktimemanagement.infra.adapter.repository;

import com.fiap.postech.hackatonworktimemanagement.domain.registro.ports.RegistroPontoFuncionarioRepositoryPort;
import com.fiap.postech.hackatonworktimemanagement.infra.adapter.entity.RegistroPontoFuncionarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistroPontoFuncionarioRepository implements RegistroPontoFuncionarioRepositoryPort {

  private final  RegistroPontoFuncionarioRepositorySQL registroPontoFuncionarioRepositorySQL;


  @Override
  public void registarBatida(RegistroPontoFuncionarioEntity registroPontoFuncionarioEntity) {
    registroPontoFuncionarioRepositorySQL.save(registroPontoFuncionarioEntity);
  }
}
