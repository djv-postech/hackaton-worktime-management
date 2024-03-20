package com.fiap.postech.hackatonworktimemanagement.application.api;

import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosCadastroPonto;
import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosFuncionario;
import com.fiap.postech.hackatonworktimemanagement.application.api.records.DadosRegistroPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.ListagemDeRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.RegistrarPontoFuncionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ponto")
public class GerenciamentoDePontoController {

    private final RegistrarPontoFuncionario registroDePonto;
    private final ListagemDeRegistro listagemDeRegistro;

    public GerenciamentoDePontoController(RegistrarPontoFuncionario registroDePonto, ListagemDeRegistro listagemDeRegistro) {
        this.registroDePonto = registroDePonto;
        this.listagemDeRegistro = listagemDeRegistro;
    }

    @PostMapping("/registrar/{matricula}")
    public ResponseEntity<DadosRegistroPonto> registrarPonto(@RequestBody DadosCadastroPonto dadosCadastroPonto){
        RegistroPontoFuncionario registroPontoFuncionario = registroDePonto.registrar(dadosCadastroPonto);
        return ResponseEntity.ok(new DadosRegistroPonto(registroPontoFuncionario.matricula,
                registroPontoFuncionario.getDataFormatada(), registroPontoFuncionario.getHoraFormatada(), registroPontoFuncionario.tipoBatida));
    }

    @PostMapping("/registros/{matricula}")
    public ResponseEntity<List<DadosRegistroPonto>> registros(@PathVariable String matricula){
        List<RegistroPontoFuncionario> registroPontoFuncionario = listagemDeRegistro
                .listarTodosOsRegistros(matricula);
        return Objects.nonNull(registroPontoFuncionario)
                ? ResponseEntity.ok(registroPontoFuncionario.stream().map(DadosRegistroPonto::new)
                .collect(Collectors.toList()))
                : ResponseEntity.notFound().build();

    }
}
