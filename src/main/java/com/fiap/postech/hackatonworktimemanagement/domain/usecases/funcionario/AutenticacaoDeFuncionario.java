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
    public Funcionario autenticar(String matricula, String senha) {
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorMatricula(matricula);
        if (funcionario != null && senha.equals(funcionario.getSenha())) {
            return funcionario;
        } else {
            return null;
        }
    }

}
