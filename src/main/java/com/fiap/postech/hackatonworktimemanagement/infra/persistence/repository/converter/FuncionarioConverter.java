package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository.converter;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioConverter {

    public Funcionario from(FuncionarioEntity funcionarioEntity) {
        return new Funcionario(funcionarioEntity.getMatricula(), funcionarioEntity.getNome(),
                funcionarioEntity.getCargo(), funcionarioEntity.getSenha());
    }
}
