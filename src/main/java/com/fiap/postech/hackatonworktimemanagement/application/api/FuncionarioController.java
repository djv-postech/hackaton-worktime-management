package com.fiap.postech.hackatonworktimemanagement.application.api;

import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosCadastroFuncionario;
import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.funcionario.Funcionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.CadastroDeFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.funcionario.ListagemDeFuncionario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionario", description = "Rest API para operações de funcionarios")
public class FuncionarioController {

    private final CadastroDeFuncionario cadastroDeFuncionario;
    private final ListagemDeFuncionario listagemDeFuncionario;

    public FuncionarioController(CadastroDeFuncionario cadastroDeFuncionario, ListagemDeFuncionario listagemDeFuncionario) {
        this.cadastroDeFuncionario = cadastroDeFuncionario;
        this.listagemDeFuncionario = listagemDeFuncionario;
    }

    @PostMapping
    private ResponseEntity<DadosFuncionario> cadastrarFuncionario(
            @RequestBody DadosCadastroFuncionario dadosCadastroFuncionario){

        Funcionario funcionario = cadastroDeFuncionario.cadastrarFuncionario(
                new Funcionario(dadosCadastroFuncionario.matricula(), dadosCadastroFuncionario.nome(),
                        dadosCadastroFuncionario.cargo(), dadosCadastroFuncionario.senha()));

        return ResponseEntity.ok(new DadosFuncionario(funcionario.getNome(), funcionario.getMatricula(),
                funcionario.getCargo().toString()));
    }

    @GetMapping("/todos")
    private ResponseEntity<List<DadosFuncionario>> todosOsFuncionarios(){

        List<Funcionario> funcionarios = listagemDeFuncionario.listarTodosOsFuncionarios();

        return Objects.nonNull(funcionarios)
                ? ResponseEntity.ok(funcionarios.stream().map(DadosFuncionario::new)
                .collect(Collectors.toList()))
                : ResponseEntity.notFound().build();
    }
}
