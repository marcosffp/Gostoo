package com.br.timn.Gosto.util;

import java.util.List;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Estabelecimento;

public class CalcularNota {
  public static double calcularMediaNotas(Estabelecimento estabelecimento) {
    // Calculando a média das avaliações do estabelecimento
    List<Avaliacao> avaliacoes = estabelecimento.getAvaliacoesEstabelecimento();
    if (avaliacoes.isEmpty()) {
      return 0.0;
    }
    double somaNotas = 0.0;
    for (Avaliacao avaliacao : avaliacoes) {
      somaNotas += avaliacao.getNotaAvaliacao();
    }
    return somaNotas / avaliacoes.size();
  }
}
