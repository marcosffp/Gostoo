package com.br.timn.Gosto.service.implementacao.crud;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.TagNotFoundException;
import com.br.timn.Gosto.model.Tag;
import com.br.timn.Gosto.repository.TagRepository;
import com.br.timn.Gosto.service.interfaces.ValidarService;
import com.br.timn.Gosto.service.interfaces.crud.TagServiceCrud;

@Service
public class ImpTagServiceCrud implements TagServiceCrud {

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private ValidarService validarService;

  @Override
  public Tag salvarTag(Long id, String nome, Long idEstabelecimento) throws IOException{
    Tag tag = new Tag();
    tag.setNomeTag(nome);

    if (id != null) {
      tag.setIdTag(id);
    }

    if (idEstabelecimento != null) {
      tag.setEstabelecimento(validarService.getEstabelecimentoById(idEstabelecimento));
    }

    return tagRepository.save(tag);
  }

  @Override
  public void atualizarTag(Long id, String nome) throws IOException {
    Tag tagExistente = validarService.validarTagExistente(id);
    tagExistente.setNomeTag(nome);
    tagRepository.save(tagExistente);
  }

  @Override
  public Tag buscarTagPorId(Long id) {
    return tagRepository.findById(id)
        .orElseThrow(() -> new TagNotFoundException("ERROR: Tag não encontrada com ID: " + id));
  }

  @Override
  public void deletarTag(Long id)  {
    Tag tag = tagRepository.findById(id)
        .orElseThrow(() -> new TagNotFoundException("ERROR: Tag não encontrada com ID: " + id));
    tagRepository.delete(tag);
  }

  @Override
  public List<Tag> mostrarTodasTags() {
    return tagRepository.findAll();
  }

}
