package com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosMensagemProcessamentoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosMes;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;

import static com.fiap.postech.hackatonworktimemanagement.infra.config.amqp.HackatonAMQPConfiguration.ESPELHO_PONTO_EX;

public class EmissaoEspelhoPonto {

    private final FuncionarioRepository funcionarioRepository;
    private final RabbitTemplate rabbitTemplate;

    public EmissaoEspelhoPonto(FuncionarioRepository funcionarioRepository,
                               RabbitTemplate rabbitTemplate) {
        this.funcionarioRepository = funcionarioRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void solicitarEspelhoDePontoMensal(String matricula, DadosMes mes, int ano) {
       validarData(mes, ano);

       Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorMatricula(matricula);

       rabbitTemplate.convertAndSend(ESPELHO_PONTO_EX, "",
               new DadosMensagemProcessamentoEspelhoPonto(matricula, mes, funcionario.getEmail(), ano));

    }

    private void validarData(DadosMes mes, int ano) {
        LocalDate dataAtual = LocalDate.now();

        if(ano > dataAtual.getYear()){
            throw new RuntimeException("Ano inválido");
        }

        if(mes.getValor() >= dataAtual.getMonth().getValue() && ano == dataAtual.getYear()){
            throw new RuntimeException("Não é possível solicitar espelho de ponto de mês atual ou data no futuro");
        }

    }
}
