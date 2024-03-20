package com.fiap.postech.hackatonworktimemanagement.infra;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPontoFuncionarioRepositoryMysql extends JpaRepository<RegistroPontoFuncionarioEntity, Integer> {

    List<RegistroPontoFuncionarioEntity> findByMatricula(String matricula);
}
