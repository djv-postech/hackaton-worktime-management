package com.fiap.postech.hackatonworktimemanagement.application.service;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosMensagemProcessamentoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.infra.smtp.EmailService;

import java.util.List;

public class ProcessamentoEspelhoPonto {

    private final EmailService emailService;
    private final RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;

    public ProcessamentoEspelhoPonto(EmailService emailService, RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository) {
        this.emailService = emailService;
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
    }

    public void processar(DadosMensagemProcessamentoEspelhoPonto dadosMensagemProcessamentoEspelhoPonto) {

        List<RegistroPontoFuncionario> registros = registroPontoFuncionarioRepository
                .listarRegistrosMensal(dadosMensagemProcessamentoEspelhoPonto.matricula(), dadosMensagemProcessamentoEspelhoPonto.mes().getValor(), dadosMensagemProcessamentoEspelhoPonto.ano());

        String relatorio = RegistroHelper.criarRelatorioEspelhoPonto(registros);

        emailService.enviarEmail(dadosMensagemProcessamentoEspelhoPonto.email(), "ESPELHO DE PONTO DO MÊS: "
                + dadosMensagemProcessamentoEspelhoPonto.mes().getNome().toUpperCase(), relatorio);
    }
}
