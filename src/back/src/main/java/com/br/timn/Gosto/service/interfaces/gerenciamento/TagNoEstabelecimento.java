package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.io.IOException;
import java.util.List;


import com.br.timn.Gosto.model.Tag;

public interface TagNoEstabelecimento {

  public void addTag(Long estabelecimentoId, String nomeTag) throws IOException;

  public void removeTag(Long estabelecimentoId, String nomeTag);

  public List<Tag> getTags(Long estabelecimentoId);
}
