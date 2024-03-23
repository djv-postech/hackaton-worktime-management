package com.fiap.postech.hackatonworktimemanagement.infra.config.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HackatonAMQPConfiguration {

    public static final String ESPELHO_PONTO_EX = "ex.espelho_ponto";
    public static final String PROCESSAR_ESPELHO_PONTO_QUEUE = "queue.processar_espelho_ponto";
    public static final String ESPELHO_PONTO_DLX = "dlx.espelho_ponto";
    public static final String PROCESSAR_ESPELHO_PONTO_DLQ = "dlq.processar_espelho_ponto";

    @Bean
    public RabbitAdmin criarAdminConfig(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> startAdmin(RabbitAdmin rabbitAdmin){
        return  event -> rabbitAdmin.initialize();
    }

    @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            return new Jackson2JsonMessageConverter();
        }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }


    @Bean
    public Queue processarEspelhoPontoQueue(){
        return QueueBuilder.nonDurable(PROCESSAR_ESPELHO_PONTO_QUEUE)
                .deadLetterExchange(ESPELHO_PONTO_DLX)
                .build();
    }

    @Bean
    public FanoutExchange espelhoPontoExchange(){
        return new FanoutExchange(ESPELHO_PONTO_EX);
    }

    @Bean
    public Binding espelhoPontoBinding(){
        return BindingBuilder.bind(processarEspelhoPontoQueue()).to(espelhoPontoExchange());

    }

    @Bean
    public FanoutExchange espelhoPontoDLX(){
        return new FanoutExchange(ESPELHO_PONTO_DLX);
    }

    @Bean
    public Queue processarEspelhoPontoDLQ(){
        return QueueBuilder.nonDurable(PROCESSAR_ESPELHO_PONTO_DLQ)
                .build();
    }

    @Bean
    public Binding espelhoPontoDLXDLQBinding(){
        return BindingBuilder.bind(processarEspelhoPontoDLQ()).to(espelhoPontoDLX());

    }

}
