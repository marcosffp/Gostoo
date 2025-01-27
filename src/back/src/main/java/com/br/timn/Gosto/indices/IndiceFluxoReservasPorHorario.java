package com.br.timn.Gosto.indices;

import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Reserva;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class IndiceFluxoReservasPorHorario implements Indice<Map<Integer, Long>> {

  @Override
  public Map<Integer, Long> calcularIndice(Estabelecimento estabelecimento) {
    return estabelecimento.getReservasEstabelecimento().stream()
        .map(Reserva::getDataHoraReserva) 
        .map(LocalDateTime::getHour) 
        .collect(Collectors.groupingBy(hour -> hour, Collectors.counting())); 
  }

  @Override
  public String getNome() {
    return "Fluxo de reservas por horário";
  }
}
