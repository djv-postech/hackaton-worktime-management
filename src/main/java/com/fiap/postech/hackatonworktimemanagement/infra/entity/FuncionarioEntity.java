package com.fiap.postech.hackatonworktimemanagement.infra.entity;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Cargo;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class FuncionarioEntity {

  @Id
  private String matricula;
  private String nome;

  @Enumerated(EnumType.STRING)
  private Cargo cargo;
  private String senha;
  private String email;

  public FuncionarioEntity() {
  }

  public FuncionarioEntity(String nome, String matricula, Cargo cargo, String senha, String email) {
    this.nome = nome;
    this.matricula = matricula;
    this.cargo = cargo;
    this.senha = senha;
    this.email = email;
  }

  public static FuncionarioEntity from(Funcionario funcionario) {
    return new FuncionarioEntity(funcionario.getNome(), funcionario.getMatricula(),
            funcionario.getCargo(), funcionario.getSenha(), funcionario.getEmail());
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

  public String getEmail() {
    return email;
  }
}
