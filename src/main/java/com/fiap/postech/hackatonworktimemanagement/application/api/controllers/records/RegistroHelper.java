package com.fiap.postech.hackatonworktimemanagement.application.api.controllers.records;

import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroHelper {

    public static DadosRegistroDiario compilarRegistrosDiario(DadosMatriculaData dadosMatriculaData, List<RegistroPontoFuncionario> registroPontoFuncionario) {
        DadosRegistroDiario dadosRegistroDiario = new DadosRegistroDiario();
        dadosRegistroDiario.setMatricula(dadosMatriculaData.matricula());
        dadosRegistroDiario.setData(dadosMatriculaData.data().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        dadosRegistroDiario.setRegistros(registroPontoFuncionario.stream()
                .map(DadosDiaHora::new)
                .collect(Collectors.toList()));

        dadosRegistroDiario.setTotalHorasTrabalhadas(calcularHorasTrabalhadasNoDia(registroPontoFuncionario));

        return dadosRegistroDiario;
    }

    private static String calcularHorasTrabalhadasNoDia(List<RegistroPontoFuncionario> registroPontoFuncionario) {
        List<LocalTime> listaHoraEntrada = registroPontoFuncionario.stream()
                .filter(r -> TipoRegistro.ENTRADA.equals(r.tipoRegistro))
                .map(RegistroPontoFuncionario::getHora)
                .collect(Collectors.toList());

        List<LocalTime> listaHoraSaida = registroPontoFuncionario.stream()
                .filter(r -> TipoRegistro.SAIDA.equals(r.tipoRegistro))
                .map(RegistroPontoFuncionario::getHora)
                .collect(Collectors.toList());

        Duration totalEntrada = Duration.ZERO;

        for (LocalTime time : listaHoraEntrada) {
            totalEntrada = totalEntrada.plus(Duration.between(LocalTime.MIN, time));
        }

        Duration totalSaida = Duration.ZERO;

        for (LocalTime time : listaHoraSaida) {
            totalSaida = totalSaida.plus(Duration.between(LocalTime.MIN, time));
        }

        Duration totalTrabalhado = totalSaida.minus(totalEntrada);
        return converterParaLocalTime(totalTrabalhado);
    }

    public static String converterParaLocalTime(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        int hours = (int) (absSeconds / 3600);
        int minutes = (int) ((absSeconds % 3600) / 60);
        int sign = seconds < 0 ? -1 : 1;
        return LocalTime.of(sign * hours, sign * minutes)
                .format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
