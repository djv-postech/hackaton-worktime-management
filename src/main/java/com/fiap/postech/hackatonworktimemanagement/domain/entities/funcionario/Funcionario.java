package com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario;

public class Funcionario {

    private String nome;
    private String matricula;
    private Cargo cargo;
    private String senha;
    private String email;

    public Funcionario(String matricula, String nome, Cargo cargo, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.senha = senha;
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
