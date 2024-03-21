package com.fiap.postech.hackatonworktimemanagement.application.config.beans;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.AutenticacaoDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.CadastroDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.ListagemDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.ListagemDeRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.RegistrarPontoFuncionario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    private final FuncionarioRepository funcionarioRepository;
    private final RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository;

    public BeanConfiguration(FuncionarioRepository funcionarioRepository, RegistroPontoFuncionarioRepository registroPontoFuncionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
        this.registroPontoFuncionarioRepository = registroPontoFuncionarioRepository;
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



}
