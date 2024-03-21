package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

public record DadosCadastroPonto(String matricula, TipoRegistro tipoRegistro) {
}
