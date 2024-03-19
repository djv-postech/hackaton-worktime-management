package com.fiap.postech.hackatonworktimemanagement.infra.adapter.repository;

import com.fiap.postech.hackatonworktimemanagement.infra.adapter.entity.RegistroPontoFuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroPontoFuncionarioRepositorySQL extends JpaRepository<RegistroPontoFuncionarioEntity, Integer> {

}
