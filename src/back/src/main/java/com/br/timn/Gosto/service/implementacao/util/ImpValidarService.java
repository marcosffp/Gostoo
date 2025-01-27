package com.br.timn.Gosto.service.implementacao.util;

import com.br.timn.Gosto.repository.*;
import com.br.timn.Gosto.service.interfaces.ValidarService;
import com.br.timn.Gosto.exception.*;
import com.br.timn.Gosto.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImpValidarService implements ValidarService {
  @Autowired
  private ImagemRepository imagemRepository;

  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private TagRepository tagRepository;

  @Override
  public Avaliacao getAvaliacaoById(Long avaliacaoId) {
    return avaliacaoRepository.findById(avaliacaoId)
        .orElseThrow(() -> new AvaliacaoNotFoundException("Avaliação não encontrada!"));
  }

  @Override
  public Avaliador getAvaliadorById(Long avaliadorId) {
    return avaliadorRepository.findById(avaliadorId)
        .orElseThrow(() -> new AvaliadorNotFoundException("Avaliador não encontrado!"));
  }

  @Override
  public Estabelecimento getEstabelecimentoById(Long estabelecimentoId) {
    return estabelecimentoRepository.findById(estabelecimentoId)
        .orElseThrow(() -> new EstabelecimentoNotFoundException("Estabelecimento não encontrado!"));
  }

  @Override
  public Imagem validarImagemExistente(Long id) {
    Imagem imagemExistente = imagemRepository.findById(id)
        .orElseThrow(() -> new ImagemNotFoundException("Imagem não encontrada!"));
    return imagemExistente;
  }

  @Override
  public void validarArquivoValido(MultipartFile arquivo) {
    if (arquivo.isEmpty()) {
      throw new IllegalArgumentException("O arquivo de imagem está vazio!");
    }
  }

  @Override
  public Tag validarTagExistente(Long id) {
    return tagRepository.findById(id)
        .orElseThrow(() -> new TagNotFoundException("Tag não encontrada!"));
  }
}
