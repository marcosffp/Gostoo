package com.br.timn.Gosto.service.interfaces.crud;

import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;

import java.io.IOException;
import java.util.List;

public interface ImagemServiceCrud {

  public void atualizarImagem(Long id, byte[] imagemBlob, String nome) throws IOException;

  public Imagem salvarImagem(byte[] imagem, String nome, Long avaliacaoId, Long avaliadorId, Long estabelecimentoId, Tipo tipo)
      throws IOException;

  public Imagem buscarImagemPorId(Long id);

  public void deletarImagem(Long id);

  public List<Imagem> mostrarTodasImagens();
}
