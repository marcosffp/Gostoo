package com.br.timn.Gosto.indices;

import com.br.timn.Gosto.model.Estabelecimento;

public interface Indice<T> {
   public T calcularIndice(Estabelecimento estabelecimento);
  public String getNome();
}
