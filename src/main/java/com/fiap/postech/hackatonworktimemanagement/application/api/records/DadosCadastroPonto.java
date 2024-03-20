package com.fiap.postech.hackatonworktimemanagement.application.api.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoBatida;

public record DadosCadastroPonto(String matricula, TipoBatida tipoBatida) {
}
