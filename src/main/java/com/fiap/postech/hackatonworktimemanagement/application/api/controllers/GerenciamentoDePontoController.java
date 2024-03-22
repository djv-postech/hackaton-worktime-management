package com.fiap.postech.hackatonworktimemanagement.application.api.controllers;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records.*;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.exceptions.RegistrosInconsistentesException;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.EmissaoEspelhoPonto;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.ListagemDeRegistro;
import com.fiap.postech.hackatonworktimemanagement.domain.usecases.registro.RegistrarPontoFuncionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
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

    @PostMapping("/registros/data")
    public ResponseEntity<DadosRegistroDiario> registrosPorDia(@RequestBody DadosMatriculaData dadosMatriculaData){
        List<RegistroPontoFuncionario> registroPontoFuncionario = listagemDeRegistro
                .listarTodosOsRegistrosDePontoPorData(dadosMatriculaData.matricula(), dadosMatriculaData.data());

        if(registroPontoFuncionario.size() % 2 != 0){
            throw new RegistrosInconsistentesException("Você tem registros inconsistentes, regularize sua situação.");
        }

        DadosRegistroDiario dadosRegistroDiario = RegistroHelper.compilarRegistrosDiario(dadosMatriculaData, registroPontoFuncionario);

        return ResponseEntity.ok(dadosRegistroDiario);

    }

    @PostMapping("/registros/espelho")
    public ResponseEntity<String> espelhoMensal(@RequestBody DadosSolicitacaoEspelho dadosSolicitacaoEspelho){

        //TODO validar a data, caso mes seja maior ou igual que o atual, e ano maior, lancar excecao
        validarMes(dadosSolicitacaoEspelho.getData());

//        emissaoEspelhoPonto.solicitarEspelhoDePontoMensal(
//                dadosSolicitacaoEspelho.getMatricula(), dadosSolicitacaoEspelho.getMes());


        return ResponseEntity.ok("Solicitação de espelho de ponto realizada");

    }

    private void validarMes(LocalDate data) {
        LocalDate dataAtual = LocalDate.now();

        if(data.getMonth().getValue() >= dataAtual.getMonth().getValue() &&
                data.getYear() > dataAtual.getYear()){
            throw new RuntimeException("Data de solicitação de espelho de ponto inválida");
        }
    }
}
