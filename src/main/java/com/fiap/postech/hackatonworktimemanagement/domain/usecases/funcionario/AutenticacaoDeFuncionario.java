package com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import org.webjars.NotFoundException;


public class AutenticacaoDeFuncionario {

    private final FuncionarioRepository funcionarioRepository;

    public AutenticacaoDeFuncionario(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    //FIXME : Criptografar e descriptografar dados sensívels de funcionarios
    public boolean autenticar(String matricula, String senha) {

        Funcionario funcionario ;
        try {
            funcionario = funcionarioRepository.buscarFuncionarioPorMatricula(matricula);
           return senha.equals(funcionario.getSenha());
           //FIXME: Caso autenticaçao esteja ok, gerar token JWT
        } catch (NotFoundException notFoundException){
            return false;
        }
    }

}
