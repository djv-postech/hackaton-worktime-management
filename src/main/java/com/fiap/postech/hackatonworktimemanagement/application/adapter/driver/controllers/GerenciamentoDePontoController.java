package com.fiap.postech.hackatonworktimemanagement.application.adapter.driver.controllers;

import com.fiap.postech.hackatonworktimemanagement.domain.registro.ports.RegistroPontoFuncionarioServicePort;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ponto")
public class GerenciamentoDePontoController {

    private RegistroPontoFuncionarioServicePort registroPontoFuncionarioServicePort;

    @PostMapping("/registrar/{matricula}")
    void registrarPonto(String matricula){
        registroPontoFuncionarioServicePort.registrarBatida(matricula);
    }
}
