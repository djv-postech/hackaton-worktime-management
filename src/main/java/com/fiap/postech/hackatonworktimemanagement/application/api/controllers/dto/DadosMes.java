package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto;

public enum DadosMes {
    JAN(1, "Janeiro"),
    FEV(2, "Fevereiro"),
    MAR(3, "Março"),
    ABR(4, "Abril"),
    MAI(5, "Maio"),
    JUN(6, "Junho"),
    JUL(7, "Julho"),
    AGO(8, "Agosto"),
    SET(9, "Setembro"),
    OUT(10, "Outubro"),
    NOV(11, "Novembro"),
    DEZ(12, "Dezembro");

    private Integer valor;
    private String nome;

    DadosMes(int valor, String nome) {

        this.valor = valor;
        this.nome = nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}
