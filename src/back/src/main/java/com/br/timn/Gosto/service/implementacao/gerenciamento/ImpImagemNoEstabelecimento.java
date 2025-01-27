package com.br.timn.Gosto.service.implementacao.gerenciamento;

import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.exception.ImagemNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.crud.ImagemServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemNoEstabelecimento;
import com.br.timn.Gosto.util.ConvertorChar256;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpImagemNoEstabelecimento implements ImagemNoEstabelecimento {

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private ImagemServiceCrud imagemServiceCrud;

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoServiceCrud;

  @Override
  public void removeImagem(Long estabelecimentoId, Long imagemId)
      throws EstabelecimentoNotFoundException, ImagemNotFoundException, RuntimeException {

    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    if (estabelecimento == null) {
      throw new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com ID: " + estabelecimentoId);
    }

    Imagem imagem = estabelecimento.getImagensEstabelecimento()
        .stream()
        .filter(img -> img.getIdImagem().equals(imagemId))
        .findFirst()
        .orElseThrow(() -> new ImagemNotFoundException("ERROR: Imagem não encontrada com ID: " + imagemId));

    estabelecimento.getImagensEstabelecimento().remove(imagem);
    imagemServiceCrud.deletarImagem(imagemId);
    estabelecimentoRepository.save(estabelecimento);

  }

  @Override
  public List<Imagem> getImagens(Long estabelecimentoId) {
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    return estabelecimento.getImagensOutrosEstabelecimento();
  }

  @Override
  public void addImagemOutro(Long estabelecimentoId, String imagemBase64)throws IOException {
    if (imagemBase64 == null || imagemBase64.isEmpty()) {
      throw new ImagemNotFoundException("ERROR: A imagem não pode ser nula ou vazia.");

    }
    byte[] imagemBytes = ConvertorChar256.convertChar256ToBytes(imagemBase64);
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    if (imagemBytes == null || imagemBytes.length == 0) {
      throw new ImagemNotFoundException("ERROR: A imagem não pode ser nula ou vazia.");
    }
    Imagem novaImagem;

    novaImagem = imagemServiceCrud.salvarImagem(
        imagemBytes, "nova_imagem", null, null,
        estabelecimento.getIdEstabelecimento(), Tipo.OUTRO);

    estabelecimento.getImagensOutrosEstabelecimento().add(novaImagem);

    estabelecimentoRepository.save(estabelecimento);

  }
}
