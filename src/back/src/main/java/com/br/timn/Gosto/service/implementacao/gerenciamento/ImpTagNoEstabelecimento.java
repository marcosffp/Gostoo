package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.TagNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Tag;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.crud.TagServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.TagNoEstabelecimento;

import java.io.IOException;

@Service
public class ImpTagNoEstabelecimento implements TagNoEstabelecimento {
  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private TagServiceCrud tagServiceCrud;

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoServiceCrud;

  @Override
  public void removeTag(Long estabelecimentoId, String nomeTag) {
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    estabelecimento.getTagsEstabelecimento().removeIf(tag -> tag.getNomeTag().equalsIgnoreCase(nomeTag));
    estabelecimentoRepository.save(estabelecimento);
  }

  @Override
  public List<Tag> getTags(Long estabelecimentoId) {
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    return estabelecimento.getTagsEstabelecimento();
  }

  @Override
  public void addTag(Long estabelecimentoId, String nomeTag)
      throws IOException {
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    boolean tagExists = estabelecimento.getTagsEstabelecimento().stream()
        .anyMatch(tag -> tag.getNomeTag().equalsIgnoreCase(nomeTag));
    if (tagExists) {
      throw new TagNotFoundException("ERROR: Tag já existe no estabelecimento com esse nome: " + nomeTag);
    }
    Tag novaTag = new Tag();
    novaTag.setNomeTag(nomeTag);
    Tag tagSalva;
    tagSalva = tagServiceCrud.salvarTag(null, nomeTag, estabelecimentoId);
    if (tagSalva == null) {
      throw new TagNotFoundException("ERROR: Tag não encontrada com nome: " + nomeTag);
    }
    estabelecimento.getTagsEstabelecimento().add(tagSalva);
    estabelecimentoRepository.save(estabelecimento);
  }
}
