package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DadosRegistroDiario {
    private String matricula;
    private String data;
    private String totalHorasTrabalhadas;
    private List<DadosDiaHora> registros = new ArrayList<>();

}
