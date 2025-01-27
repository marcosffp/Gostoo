package com.br.timn.Gosto.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.Tag;

public interface ValidarService {
  public Avaliacao getAvaliacaoById(Long avaliacaoId);

  public Avaliador getAvaliadorById(Long avaliadorId);

  public Estabelecimento getEstabelecimentoById(Long estabelecimentoId);

  public Imagem validarImagemExistente(Long id);

  public void validarArquivoValido(MultipartFile arquivo);

  public Tag validarTagExistente(Long id);

}
