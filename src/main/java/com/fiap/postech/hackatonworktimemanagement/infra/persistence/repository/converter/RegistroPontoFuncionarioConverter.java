package com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository.converter;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistroPontoFuncionarioConverter {

    public RegistroPontoFuncionario convertFrom(RegistroPontoFuncionarioEntity registroPontoFuncionarioEntity) {
        return new RegistroPontoFuncionario(registroPontoFuncionarioEntity.getMatricula(), registroPontoFuncionarioEntity.getData(),
                registroPontoFuncionarioEntity.getHora(), registroPontoFuncionarioEntity.getTipoBatida());
    }

    public List<RegistroPontoFuncionario> convertToDomainList(List<RegistroPontoFuncionarioEntity> entity) {
        List<RegistroPontoFuncionario> registros = new ArrayList<>();
        entity.forEach(registro -> {
            registros.add(new RegistroPontoFuncionario(registro.getMatricula(), registro.getData(),
                    registro.getHora(), registro.getTipoBatida()));
        });

        return registros;
    }
}
