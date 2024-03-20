package com.fiap.postech.hackatonworktimemanagement.infra;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.FuncionarioRepository;
import com.fiap.postech.hackatonworktimemanagement.infra.converter.FuncionarioConverter;
import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuncionarioRepositoryImpl implements FuncionarioRepository {

    private final FuncionarioRepositoryMysql funcionarioRepositoryMysql;
    private final FuncionarioConverter funcionarioConverter;

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = funcionarioRepositoryMysql.save(FuncionarioEntity.from(funcionario));
        return funcionarioConverter.from(funcionarioEntity);
    }

    @Override
    public List<Funcionario> todosOsFuncionarios() {
        return funcionarioRepositoryMysql.findAll().stream()
                .map(funcionarioConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public Funcionario buscarFuncionarioPorMatricula(String matricula) {
        Optional<FuncionarioEntity> funcionarioEntity =  this.funcionarioRepositoryMysql.findByMatricula(matricula);
        return funcionarioEntity.map(funcionarioConverter::from)
                .orElseThrow(() -> new NotFoundException(
                        "Funcionário de matricula: " + matricula + " não encontrado"));
    }


}