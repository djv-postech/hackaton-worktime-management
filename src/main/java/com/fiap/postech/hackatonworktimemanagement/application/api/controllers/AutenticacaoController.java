package com.fiap.postech.hackatonworktimemanagement.application.api.controllers;

import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosAutenticacaoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.AutenticacaoDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.infra.security.JwtTokenUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@Tag(name = "Autenticacao", description = "Rest API para operações de autenticação")

public class AutenticacaoController {
    private final AutenticacaoDeFuncionario autenticacaoDeFuncionario;

    @Autowired
    JwtTokenUtils jwtTokenUtils;


    public AutenticacaoController(AutenticacaoDeFuncionario autenticacaoDeFuncionario) {
        this.autenticacaoDeFuncionario = autenticacaoDeFuncionario;
    }

    @PostMapping
    private ResponseEntity<?> autenticarFuncionario(@RequestBody DadosAutenticacaoFuncionario dadosAutenticacaoFuncionarios) {

        try {
            Funcionario funcionario = autenticacaoDeFuncionario.autenticar(dadosAutenticacaoFuncionarios.matricula(), dadosAutenticacaoFuncionarios.senha());
            String token = jwtTokenUtils.gerarToken(funcionario.getNome());
            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        }

    }
}
