package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository.converter;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistroPontoFuncionarioConverter {

    public RegistroPontoFuncionario convertFrom(RegistroPontoFuncionarioEntity registroPontoFuncionarioEntity) {
        return new RegistroPontoFuncionario(registroPontoFuncionarioEntity.getMatricula(), registroPontoFuncionarioEntity.getData(),
                registroPontoFuncionarioEntity.getHora(), registroPontoFuncionarioEntity.getTipoBatida());
    }
}
