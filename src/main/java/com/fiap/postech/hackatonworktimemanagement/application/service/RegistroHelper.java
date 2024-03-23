package com.fiap.postech.hackatonworktimemanagement.application.service;

import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosDiaHora;
import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosMatriculaData;
import com.fiap.postech.hackatonworktimemanagement.application.api.controllers.dto.DadosRegistroDiario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.RegistroPontoFuncionario;
import com.fiap.postech.hackatonworktimemanagement.domain.entities.registro.TipoRegistro;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    public static String calcularHorasTrabalhadasNoDia(List<RegistroPontoFuncionario> registroPontoFuncionario) {
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

    public static String getMesEmPortugues(int monthValue) {
        Locale locale = new Locale("pt", "BR")
        return new DateFormatSymbols(locale).getMonths()[monthValue-1];
    }

    public static String criarRelatorioEspelhoPonto(List<RegistroPontoFuncionario> registros) {

        StringBuilder tabela = new StringBuilder();

        var mes = RegistroHelper.getMesEmPortugues(registros.get(0).getData().getMonthValue());
        var totalDeHoras = sumarizarHoras(registros);

        tabela.append("\nESPELHO DE PONTO DO MES DE: ").append(mes.toUpperCase()).append("\n\n");

        Map<LocalDate, StringBuilder> linhasPorData = new HashMap<>();

        for (RegistroPontoFuncionario registro : registros) {
            LocalDate data = registro.getData();
            StringBuilder linha = linhasPorData.getOrDefault(data, new StringBuilder(data.toString()));
            linha.append("\t").append(registro.getHoraFormatada());
            linhasPorData.put(data, linha);
        }


        for (Map.Entry<LocalDate, StringBuilder> entry : linhasPorData.entrySet()) {
            tabela.append(entry.getValue()).append("\n");
        }

        tabela.append("\nTOTAL DE HORAS TRABALHADAS NO MES: ").append(totalDeHoras).append("\n");

        System.out.println(tabela);
        return tabela.toString();
    }

    static String sumarizarHoras(List<RegistroPontoFuncionario> registros) {
        Long totalHorasTrabalhadas = 0L;
        LocalDate dataAtual = null;
        LocalTime entrada = null;
        for (RegistroPontoFuncionario registro : registros) {
            if (registro.getTipoBatida() == TipoRegistro.ENTRADA) {
                entrada = registro.getHora();
            } else {
                if (entrada != null && registro.getData().equals(dataAtual)) {
                    totalHorasTrabalhadas += calcularDiferencaHoras(entrada, registro.getHora());
                }
            }
            dataAtual = registro.getData();
        }
        return RegistroHelper.getHorasTrabalhadasNoMes(totalHorasTrabalhadas);
    }

    static Long calcularDiferencaHoras(LocalTime inicio, LocalTime fim) {
        Duration totalEntrada = Duration.ZERO;
        totalEntrada = totalEntrada.plus(Duration.between(LocalTime.MIN, inicio));

        Duration totalSaida = Duration.ZERO;
        totalSaida = totalSaida.plus(Duration.between(LocalTime.MIN, fim));

        return totalSaida.minus(totalEntrada).getSeconds();

    }

    public static String getHorasTrabalhadasNoMes(Long s) {
        long absSeconds = Math.abs(s);
        int hours = (int) (absSeconds / 3600);
        int minutes = (int) ((absSeconds % 3600) / 60);
        return hours + " HORAS E " + minutes + " MINUTOS";

    }
}
