package com.br.timn.Gosto.indices;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Estabelecimento;

import java.util.HashMap;
import java.util.Map;

public class IndiceDistribuicaoNotas implements Indice<Map<String, Double>> {

    @Override
    public Map<String, Double> calcularIndice(Estabelecimento estabelecimento) {
      
        int totalAvaliacoes = estabelecimento.getAvaliacoesEstabelecimento().size();
        if (totalAvaliacoes == 0) {
            return new HashMap<>(); 
        }

        int faixa1 = 0; 
        int faixa2 = 0; 
        int faixa3 = 0;

        for (Avaliacao avaliacao : estabelecimento.getAvaliacoesEstabelecimento()) {
            double nota = avaliacao.getNotaAvaliacao();
            if (nota >= 1 && nota <= 2) {
                faixa1++;
            } else if (nota >= 3 && nota <= 4) {
                faixa2++;
            } else if (nota == 5) {
                faixa3++;
            }
        }

        double percentualFaixa1 = (faixa1 / (double) totalAvaliacoes) * 100;
        double percentualFaixa2 = (faixa2 / (double) totalAvaliacoes) * 100;
        double percentualFaixa3 = (faixa3 / (double) totalAvaliacoes) * 100;

        Map<String, Double> distribuicao = new HashMap<>();
        distribuicao.put("1-2", percentualFaixa1);
        distribuicao.put("3-4", percentualFaixa2);
        distribuicao.put("5", percentualFaixa3);
        return distribuicao;
    }

    @Override
    public String getNome() {
        return "Distribuição de Notas";
    }
}
