package com.fiap.postech.hackatonworktimemanagement.application.api.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

public record DadosCadastroPonto(String matricula, TipoRegistro tipoRegistro) {
}
