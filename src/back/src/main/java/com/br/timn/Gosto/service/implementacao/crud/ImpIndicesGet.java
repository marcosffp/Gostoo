package com.br.timn.Gosto.service.implementacao.crud;



import com.br.timn.Gosto.indices.GerenciamentoIndices;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.crud.IndicesGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImpIndicesGet implements IndicesGet {

  @Autowired
  private GerenciamentoIndices gerenciamentoIndices;

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoService;

  @Override
  public Map<String, Object> calcularTodosIndices(Long idEstabelecimento)  {
    Estabelecimento estabelecimento = estabelecimentoService.getEstabelecimentoById(idEstabelecimento);
    if (estabelecimento == null) {
      throw new IllegalArgumentException("ERROR: Estabelecimento não encontrado com ID: " + idEstabelecimento);
    }
    return gerenciamentoIndices.calcularTodosIndices(estabelecimento);
  }

  @Override
  public Object calcularIndiceEspecifico(Long idEstabelecimento, String indiceNome) {
    Map<String, Object> resultadosIndices = calcularTodosIndices(idEstabelecimento);
    return resultadosIndices.get(indiceNome);
  }

}
