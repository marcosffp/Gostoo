package com.br.timn.Gosto.service.implementacao.crud;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.ImagemNotFoundException;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.repository.ImagemRepository;
import com.br.timn.Gosto.service.interfaces.ValidarService;
import com.br.timn.Gosto.service.interfaces.crud.ImagemServiceCrud;

@Service
public class ImpImagemServiceCrud implements ImagemServiceCrud {

  @Autowired
  private ImagemRepository imagemRepository;

  @Autowired
  private ValidarService validarService;

  @Override
  public void atualizarImagem(Long id, byte[] imagemBlob, String nome) throws IOException {
    Imagem imagemExistente = validarService.validarImagemExistente(id);
    imagemExistente.setBlobImagem(imagemBlob);
    imagemExistente.setNomeImagem(nome);
    imagemRepository.save(imagemExistente);
  }

  @Override
  public Imagem salvarImagem(byte[] imagem, String nome, Long avaliacaoId, Long avaliadorId, Long estabelecimentoId,
      Tipo tipo) throws IOException {
    Imagem img = new Imagem();
    img.setNomeImagem(nome);
    img.setBlobImagem(imagem);
    img.setTipoImagem(tipo);

    if (avaliacaoId != null) {
      img.setAvaliacaoImagem(validarService.getAvaliacaoById(avaliacaoId));
    }

    if (avaliadorId != null) {
      img.setAvaliadorImagem(validarService.getAvaliadorById(avaliadorId));
    }

    if (estabelecimentoId != null) {
      img.setEstabelecimentoImagem(validarService.getEstabelecimentoById(estabelecimentoId));
    }

    return imagemRepository.save(img);
  }

  @Override
  public Imagem buscarImagemPorId(Long id)  {
    return imagemRepository.findById(id)
        .orElseThrow(() -> new ImagemNotFoundException("ERROR: Imagem não encontrada com ID: " + id));
  }

  @Override
  public void deletarImagem(Long id) {
    Imagem imagem = imagemRepository.findById(id)
        .orElseThrow(() -> new ImagemNotFoundException("ERROR: Imagem não encontrada com ID: " + id));
    imagemRepository.delete(imagem);
  }

  @Override
  public List<Imagem> mostrarTodasImagens() {
    return imagemRepository.findAll();
  }
}
