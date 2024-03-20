package com.fiap.postech.hackatonworktimemanagement.infra.entity;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Cargo;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FuncionarioEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String matricula;
  private Cargo cargo;
  private String senha;

  public FuncionarioEntity() {
  }

  public FuncionarioEntity(String nome, String matricula, Cargo cargo, String senha) {
    this.nome = nome;
    this.matricula = matricula;
    this.cargo = cargo;
    this.senha = senha;
  }

  public static FuncionarioEntity from(Funcionario funcionario) {
    return new FuncionarioEntity(funcionario.getNome(), funcionario.getMatricula(),
            funcionario.getCargo(), funcionario.getSenha());
  }

  public Integer getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getMatricula() {
    return matricula;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public String getSenha() {
    return senha;
  }
}
