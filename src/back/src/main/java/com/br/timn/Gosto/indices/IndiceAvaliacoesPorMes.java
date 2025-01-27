package com.br.timn.Gosto.indices;


import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Avaliacao;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class IndiceAvaliacoesPorMes implements Indice<Map<Integer, Long>> {

  @Override
  public Map<Integer, Long> calcularIndice(Estabelecimento estabelecimento) {
    return estabelecimento.getAvaliacoesEstabelecimento().stream()
        .map(Avaliacao::getDataAvaliacao) 
        .map(LocalDate::getMonthValue) 
        .collect(Collectors.groupingBy(mes -> mes, Collectors.counting())); 
  }

  @Override
  public String getNome() {
    return "Avaliações por mês";
  }
}
