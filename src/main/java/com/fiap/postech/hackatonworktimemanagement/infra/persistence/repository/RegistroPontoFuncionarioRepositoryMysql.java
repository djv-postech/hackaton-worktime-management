package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroPontoFuncionarioRepositoryMysql extends JpaRepository<RegistroPontoFuncionarioEntity, Integer> {

    List<RegistroPontoFuncionarioEntity> findByMatriculaAndData(String matricula, LocalDate data);
}
