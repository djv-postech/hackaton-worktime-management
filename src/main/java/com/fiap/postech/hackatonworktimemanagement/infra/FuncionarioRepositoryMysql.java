package com.fiap.postech.hackatonworktimemanagement.infra;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepositoryMysql extends JpaRepository<FuncionarioEntity, Integer> {

    FuncionarioEntity findByMatricula(String matricula);
}
