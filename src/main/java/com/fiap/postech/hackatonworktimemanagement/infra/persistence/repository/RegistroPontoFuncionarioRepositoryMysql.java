package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroPontoFuncionarioRepositoryMysql extends JpaRepository<RegistroPontoFuncionarioEntity, Integer> {

    List<RegistroPontoFuncionarioEntity> findByMatriculaAndData(String matricula, LocalDate data);

    List<RegistroPontoFuncionarioEntity> findByMatricula(String matricula);

    @Query("SELECT r FROM RegistroPontoFuncionarioEntity r WHERE r.matricula = ?1 AND MONTH(r.data) = ?2 AND YEAR(r.data) = ?3")
    List<RegistroPontoFuncionarioEntity> listarRegistrosMensal(String matricula, Integer mes, Integer ano);
}
