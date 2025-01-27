package com.br.timn.Gosto.service.interfaces.crud;

import java.io.IOException;
import java.util.List;

import com.br.timn.Gosto.model.Tag;

public interface TagServiceCrud {

  public void atualizarTag(Long id, String nome) throws IOException;

  public Tag salvarTag(Long id, String nome, Long idEstabelecimento) throws IOException;

  public Tag buscarTagPorId(Long id);

  public void deletarTag(Long id);

  public List<Tag> mostrarTodasTags();



}
