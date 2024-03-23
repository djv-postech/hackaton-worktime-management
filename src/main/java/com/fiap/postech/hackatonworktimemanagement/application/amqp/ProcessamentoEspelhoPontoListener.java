package com.fiap.postech.hackatonworktimemanagement.application.amqp;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosMensagemProcessamentoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.application.service.ProcessamentoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.infra.config.amqp.HackatonAMQPConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessamentoEspelhoPontoListener {

    private final ProcessamentoEspelhoPonto processamentoEspelhoPonto;

    public ProcessamentoEspelhoPontoListener(ProcessamentoEspelhoPonto processamentoEspelhoPonto) {
        this.processamentoEspelhoPonto = processamentoEspelhoPonto;
    }

    @RabbitListener(queues = HackatonAMQPConfiguration.PROCESSAR_ESPELHO_PONTO_QUEUE)
    public void notificar(DadosMensagemProcessamentoEspelhoPonto dadosMensagemProcessamentoEspelhoPonto){
        processamentoEspelhoPonto.processar(dadosMensagemProcessamentoEspelhoPonto);
    }
}
