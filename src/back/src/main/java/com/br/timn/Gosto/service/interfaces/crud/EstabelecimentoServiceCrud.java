package com.br.timn.Gosto.service.interfaces.crud;

import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.update.UpdateEstabelecimento;

import java.io.IOException;
import java.util.List;

public interface EstabelecimentoServiceCrud {

  public Estabelecimento createEstabelecimento(Estabelecimento estabelecimento) throws Exception;

  public Estabelecimento getEstabelecimentoById(Long id); 

  public List<Estabelecimento> getAllEstabelecimentos();

  public Estabelecimento getEstabelecimentoByEmail(String email);

  public void deleteEstabelecimento(Long id);

  public Estabelecimento updateEstabelecimento(UpdateEstabelecimento update) throws Exception,IOException;
}
