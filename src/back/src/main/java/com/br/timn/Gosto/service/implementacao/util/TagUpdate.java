package com.br.timn.Gosto.service.implementacao.util;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Tag;
import com.br.timn.Gosto.service.interfaces.crud.TagServiceCrud;

@Service
public class TagUpdate {

  @Autowired
  private TagServiceCrud tagServiceCrud;

  public Tag updateTag(Estabelecimento estabelecimento, String nome) {
    Tag tagExistente = null;
    try {
      tagExistente = estabelecimento.getTagsEstabelecimento().stream()
          .filter(tag -> tag.getNomeTag().equalsIgnoreCase(nome))
          .findFirst()
          .orElse(null);
      if (tagExistente != null) {
        tagExistente.setNomeTag(nome);
        return tagServiceCrud.salvarTag(tagExistente.getIdTag(), nome, estabelecimento.getIdEstabelecimento());
      } else {
        Tag novaTag = new Tag();
        novaTag.setNomeTag(nome);
        Tag tagSalva = tagServiceCrud.salvarTag(null, nome, estabelecimento.getIdEstabelecimento());
        estabelecimento.getTagsEstabelecimento().add(tagSalva);
        return tagSalva;
      }
    } catch (IOException e) {
      System.err.println("Erro ao atualizar ou salvar a tag: " + e.getMessage());
      return null; 
    }
  }
}
