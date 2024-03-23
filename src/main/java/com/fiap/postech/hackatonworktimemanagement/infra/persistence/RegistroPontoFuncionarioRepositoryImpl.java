package com.fiap.postech.hackatonworktimemanagement.infra.persistence;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository.RegistroPontoFuncionarioRepositoryMysql;
import com.fiap.postech.hackatonworktimemanagement.infra.persistence.repository.converter.RegistroPontoFuncionarioConverter;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.RegistroPontoFuncionarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    public List<RegistroPontoFuncionario> listarTodosOsRegistrosPorData(String matricula, LocalDate data) {
        return registroPontoFuncionarioRepositoryMysql.findByMatriculaAndData(matricula, data)
                .stream()
                .map(registroPontoFuncionarioConverter::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistroPontoFuncionario> listarTodosOsRegistrosPorMatricula(String matricula) {
        return registroPontoFuncionarioRepositoryMysql.findByMatricula(matricula)
                .stream()
                .map(registroPontoFuncionarioConverter::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistroPontoFuncionario> listarRegistrosMensal(String matricula, Integer mes, Integer ano) {
        List<RegistroPontoFuncionarioEntity> registros = registroPontoFuncionarioRepositoryMysql
                .listarRegistrosMensal(matricula, mes, ano);

        if(Objects.isNull(registros)){
            throw new RuntimeException("Não há registros para da data informada");
        }

        return registroPontoFuncionarioConverter.convertToDomainList(registros);

    }


}