package com.br.timn.Gosto.service.interfaces.crud;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.get.GetAvaliacao;
import com.br.timn.Gosto.model.update.UpdateAvaliacao;

public interface AvaliacaoServiceCrud {
  public Avaliacao createAvaliacao(Map<String, Object> dados) throws Exception, IOException;

  public GetAvaliacao getAvaliacaoById(Long id);

  public List<GetAvaliacao> getAllAvaliacoes();

  public void deleteAvaliacao(Long id);

  public Avaliacao updateAvaliacao(UpdateAvaliacao update) throws Exception;
}
