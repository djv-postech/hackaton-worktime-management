package com.fiap.postech.hackatonworktimemanagement.application.api.controllers;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.*;
import com.fiap.postech.hackatonworktimemanagement.application.service.RegistroHelper;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.exceptions.RegistrosInconsistentesException;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.EmissaoEspelhoPonto;
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
    private final EmissaoEspelhoPonto emissaoEspelhoPonto;

    public GerenciamentoDePontoController(RegistrarPontoFuncionario registroDePonto, ListagemDeRegistro listagemDeRegistro, EmissaoEspelhoPonto emissaoEspelhoPonto) {
        this.registroDePonto = registroDePonto;
        this.listagemDeRegistro = listagemDeRegistro;
        this.emissaoEspelhoPonto = emissaoEspelhoPonto;
    }

    @PostMapping("/registrar")
    public ResponseEntity<DadosRegistroPonto> registrarPonto(@RequestBody DadosCadastroPonto dadosCadastroPonto){
        RegistroPontoFuncionario registroPontoFuncionario = registroDePonto.registrar(dadosCadastroPonto);
        return ResponseEntity.ok(new DadosRegistroPonto(registroPontoFuncionario.matricula,
                registroPontoFuncionario.getDataFormatada(), registroPontoFuncionario.getHoraFormatada(), registroPontoFuncionario.tipoRegistro));
    }

    @PostMapping("/registros/diario")
    public ResponseEntity<DadosRegistroDiario> registrosPorDia(@RequestBody DadosMatriculaData dadosMatriculaData){
        List<RegistroPontoFuncionario> registroPontoFuncionario = listagemDeRegistro
                .listarTodosOsRegistrosDePontoPorData(dadosMatriculaData.matricula(), dadosMatriculaData.data());

        if(registroPontoFuncionario.size() % 2 != 0){
            throw new RegistrosInconsistentesException("Você tem registros inconsistentes, regularize sua situação.");
        }

        DadosRegistroDiario dadosRegistroDiario = RegistroHelper.compilarRegistrosDiario(dadosMatriculaData, registroPontoFuncionario);

        return ResponseEntity.ok(dadosRegistroDiario);

    }

    @PostMapping("/registros/mensal")
    public ResponseEntity<String> espelhoMensal(@RequestBody DadosSolicitacaoEspelho dadosSolicitacaoEspelho){
        emissaoEspelhoPonto.solicitarEspelhoDePontoMensal(dadosSolicitacaoEspelho.getMatricula(),
                dadosSolicitacaoEspelho.getMes(), dadosSolicitacaoEspelho.getAno());
        return ResponseEntity.ok("Solicitação de espelho de ponto realizada");

    }

}
