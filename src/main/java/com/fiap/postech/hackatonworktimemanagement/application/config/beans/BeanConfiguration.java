package com.fiap.postech.hackatonworktimemanagement.application.config.beans;

import com.fiap.postech.hackatonworktimemanagement.application.service.ProcessamentoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.AutenticacaoDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.CadastroDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.ListagemDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.EmissaoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.ListagemDeRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.RegistrarPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.infra.smtp.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mail.javamail.JavaMailSender;

import javax.sql.DataSource;


@Configuration
public class BeanConfiguration {

    private final FuncionarioRepository funcionarioRepository;
    private final RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;
    private final RabbitTemplate rabbitTemplate;
    private final JavaMailSender javaMailSender;

    public BeanConfiguration(FuncionarioRepository funcionarioRepository, RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository, RabbitTemplate rabbitTemplate, JavaMailSender javaMailSender){
        this.funcionarioRepository = funcionarioRepository;
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.javaMailSender = javaMailSender;
    }

    @Bean
    public CadastroDeFuncionario cadastroDeFuncionario(){
        return new CadastroDeFuncionario(funcionarioRepository);
    }

    @Bean
    public ListagemDeFuncionario listagemDeFuncionario(){
        return new ListagemDeFuncionario(funcionarioRepository);
    }

    @Bean
    public RegistrarPontoFuncionario registrarPontoFuncionario(){
        return new RegistrarPontoFuncionario(registroPontoFuncionarioRepository,funcionarioRepository);
    }

    @Bean
    public ListagemDeRegistro listagemDeRegistro(){
        return new ListagemDeRegistro(registroPontoFuncionarioRepository);
    }

    @Bean
    public AutenticacaoDeFuncionario autenticacaoDeFuncionario(){
        return new AutenticacaoDeFuncionario(funcionarioRepository);
    }

    @Bean
    public EmissaoEspelhoPonto emissaoEspelhoPonto(){
        return new EmissaoEspelhoPonto(funcionarioRepository, rabbitTemplate);
    }

    @Bean
    public EmailService email(){
        return new EmailService(javaMailSender);
    }

    @Bean
    public ProcessamentoEspelhoPonto processamentoEspelhoPonto(){
        return new ProcessamentoEspelhoPonto(email(), registroPontoFuncionarioRepository);
    }


    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }



}
