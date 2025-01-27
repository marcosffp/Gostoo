package com.br.timn.Gosto.service.interfaces.crud;

import java.util.Map;


public interface IndicesGet {
  public Map<String, Object> calcularTodosIndices(Long idEstabelecimento);

  public Object calcularIndiceEspecifico(Long idEstabelecimento, String indiceNome);
}
