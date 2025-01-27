package com.br.timn.Gosto.indices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


import com.br.timn.Gosto.model.Estabelecimento;

@Component
public class GerenciamentoIndices {

  private final List<Indice<?>> indices = new ArrayList<>();

  public void adicionarIndice(Indice<?> indice) {
    System.out.println("Adicionando índice: " + indice.getNome());
    indices.add(indice);
  }

  public GerenciamentoIndices() {
    adicionarIndicesAvaliacoes();
    adicionarIndicesReservas();
  }


  public void adicionarIndicesAvaliacoes() {
    this.adicionarIndice(new IndiceAvaliacoesPorMes());
    this.adicionarIndice(new IndiceDistribuicaoNotas());;

  }


  public void adicionarIndicesReservas() {
    this.adicionarIndice(new IndiceCapacidadeMediaPorMesa());
    this.adicionarIndice(new IndiceFluxoReservasPorHorario());
    this.adicionarIndice(new IndiceQuantidadeReservas());
  }


  public Map<String, Object> calcularTodosIndices(Estabelecimento estabelecimento) {
    Map<String, Object> resultados = new HashMap<>();

    for (Indice<?> indice : indices) {
      String nomeIndice = indice.getNome();
      Object resultado = indice.calcularIndice(estabelecimento);
      resultados.put(nomeIndice, resultado);
    }

    return resultados;
  }
}