package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

public record DadosCadastroPonto(String matricula, TipoRegistro tipoRegistro) {
}
