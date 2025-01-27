package com.br.timn.Gosto.indices;

import com.br.timn.Gosto.model.Estabelecimento;

public class IndiceQuantidadeReservas implements Indice<Integer> {
    @Override
    public Integer calcularIndice(Estabelecimento estabelecimento) {
        return estabelecimento.getReservasEstabelecimento().size();
    }

    @Override
    public String getNome() {
      return "Quantidade de reservas";
    }
  
}
