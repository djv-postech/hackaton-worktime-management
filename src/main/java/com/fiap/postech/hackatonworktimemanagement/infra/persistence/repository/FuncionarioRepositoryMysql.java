package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepositoryMysql extends JpaRepository<FuncionarioEntity, Integer> {

    Optional<FuncionarioEntity> findByMatricula(String matricula);
}
