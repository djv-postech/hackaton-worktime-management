package com.fiap.postech.hackatonworktimemanagement.infra;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.infra.converter.RegistroPontoFuncionarioConverter;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistroPontoFuncionarioRepositoryImpl implements RegistroPontoFuncionarioRepository {

    private final RegistroPontoFuncionarioRepositoryMysql registroPontoFuncionarioRepositoryMysql;
    private final RegistroPontoFuncionarioConverter registroPontoFuncionarioConverter;

    public RegistroPontoFuncionario registrarPonto(RegistroPontoFuncionario registroPontoFuncionario) {
        RegistroPontoFuncionarioEntity registroPontoFuncionarioEntity = registroPontoFuncionarioRepositoryMysql.save(
                RegistroPontoFuncionarioEntity.from(registroPontoFuncionario));
        return registroPontoFuncionarioConverter.convertFrom(registroPontoFuncionarioEntity);
    }

    @Override
    public List<RegistroPontoFuncionario> listarTodosOsRegistros(String matricula) {
        return registroPontoFuncionarioRepositoryMysql.findByMatricula(matricula)
                .stream()
                .map(registroPontoFuncionarioConverter::convertFrom)
                .collect(Collectors.toList());
    }


}