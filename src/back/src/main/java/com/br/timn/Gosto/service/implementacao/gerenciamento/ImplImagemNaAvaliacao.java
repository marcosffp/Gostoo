package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.AvaliacaoNotFoundException;
import com.br.timn.Gosto.exception.AvaliadorNotFoundException;
import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.exception.ImagemNotFoundException;
import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.repository.AvaliacaoRepository;
import com.br.timn.Gosto.service.interfaces.crud.ImagemServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemNaAvaliacao;
import com.br.timn.Gosto.util.ConvertorChar256;

@Service
public class ImplImagemNaAvaliacao implements ImagemNaAvaliacao {

  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  @Autowired
  private ImagemServiceCrud imagemServiceCrud;

  @Override
  public void addImagemAvaliacao(Long avaliacaoId, String imagemBase64)
      throws IOException {
    if (imagemBase64 == null || imagemBase64.isEmpty()) {
      throw new ImagemNotFoundException("ERROR: Imagem não pode ser nula ou vazia!");
    }
    byte[] imagemBytes = ConvertorChar256.convertChar256ToBytes(imagemBase64);
    Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliação não encontrada com ID: " + avaliacaoId));

    Avaliador avaliador = avaliacao.getAvaliador();
    if (avaliador == null) {
      throw new AvaliadorNotFoundException("ERROR: Avaliador não encontrado com ID: " + avaliacaoId);
    }
    Estabelecimento estabelecimento = avaliacao.getEstabelecimento();
    if (estabelecimento == null) {
      throw new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com ID: " + avaliacaoId);
    }
    if (imagemBytes == null || imagemBytes.length == 0) {
      throw new ImagemNotFoundException("ERROR: Imagem não pode ser nula ou vazia!");
    }
    Imagem novaImagem;

    novaImagem = imagemServiceCrud.salvarImagem(
        imagemBytes, "imagem_avaliacao", avaliacaoId, null,
        null, Tipo.OUTRO);

    avaliacao.getImagensAvaliacao().add(novaImagem);
    avaliador.addAvaliacaoAvaliador(avaliacao);
    estabelecimento.addAvaliacaoEstabelecimento(avaliacao);
    avaliacaoRepository.save(avaliacao);
  }

  @Override
  public void removeImagem(Long avaliacaoId, Long imagemId) throws IOException {
    Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliação não encontrada com ID: " + avaliacaoId));
    if (avaliacao == null) {
      throw new AvaliacaoNotFoundException("ERROR: Avaliação não encontrada com ID: " + avaliacaoId);
    }

    Imagem imagem = avaliacao.getImagensAvaliacao()
        .stream()
        .filter(img -> img.getIdImagem().equals(imagemId))
        .findFirst()
        .orElseThrow(() -> new ImagemNotFoundException("ERROR: Imagem não encontrada com ID: " + imagemId));

    avaliacao.getImagensAvaliacao().remove(imagem);
    imagemServiceCrud.deletarImagem(imagemId);

    avaliacaoRepository.save(avaliacao);

  }

  @Override
  public List<Imagem> getImagens(Long avaliacaoId) {
    Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliação não encontrada com ID: " + avaliacaoId));
    return avaliacao.getImagensAvaliacao();
  }

}
