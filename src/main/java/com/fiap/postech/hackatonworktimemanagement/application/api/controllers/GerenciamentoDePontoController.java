package com.fiap.postech.hackatonworktimemanagement.application.api.controllers;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records.*;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.ListagemDeRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.RegistrarPontoFuncionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
                registroPontoFuncionario.getDataFormatada(), registroPontoFuncionario.getHoraFormatada(), registroPontoFuncionario.tipoRegistro));
    }

    @PostMapping("/registros/data")
    public ResponseEntity<DadosRegistroDiario> registrosPorDia(@RequestBody DadosMatriculaData dadosMatriculaData){
        List<RegistroPontoFuncionario> registroPontoFuncionario = listagemDeRegistro
                .listarTodosOsRegistrosDePontoPorData(dadosMatriculaData.matricula(), dadosMatriculaData.data());

        if(registroPontoFuncionario.size() % 2 != 0){
            throw new RuntimeException("Você tem batidas inconsistêntes, regularize sua situação.");
        }

        DadosRegistroDiario dadosRegistroDiario = RegistroHelper.compilarRegistrosDiario(dadosMatriculaData, registroPontoFuncionario);

        return ResponseEntity.ok(dadosRegistroDiario);

    }
}
