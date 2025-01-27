package com.br.timn.Gosto.indices;

import com.br.timn.Gosto.model.Estabelecimento;

public class IndiceCapacidadeMediaPorMesa implements Indice<Double> {

  @Override
  public Double calcularIndice(Estabelecimento estabelecimento) {

    if (estabelecimento.getMesasEstabelecimento() == null || estabelecimento.getMesasEstabelecimento().isEmpty()) {
      return 0.0;
    }
    return estabelecimento.getMesasEstabelecimento().stream()
        .mapToInt(mesa -> mesa.getCapacidadeMesa())
        .average() 
        .orElse(0.0); 
  }

  @Override
  public String getNome() {
    return "Capacidade média por mesa";
  }
}
