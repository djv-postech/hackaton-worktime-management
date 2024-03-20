package com.fiap.postech.hackatonworktimemanagement.application.api;

import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.AutenticacaoDeFuncionario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@Tag(name = "Autenticacao", description = "Rest API para operações de autenticação")

public class AutenticacaoController {
    private final AutenticacaoDeFuncionario autenticacaoDeFuncionario;


    public AutenticacaoController(AutenticacaoDeFuncionario autenticacaoDeFuncionario) {
        this.autenticacaoDeFuncionario = autenticacaoDeFuncionario;
    }

    @GetMapping("{matricula}/{senha}")
    private ResponseEntity<String> autenticarFuncionario(@PathVariable String matricula, @PathVariable String senha) {

        if (!autenticacaoDeFuncionario.autenticar(matricula, senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        } else {
            return ResponseEntity.ok("Gerar o token para ser utilizado como autenticaçao");
        }

    }
}
