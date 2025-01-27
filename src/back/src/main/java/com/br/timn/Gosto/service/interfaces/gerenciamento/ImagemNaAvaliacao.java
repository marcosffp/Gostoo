package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.io.IOException;
import java.util.List;
import com.br.timn.Gosto.model.Imagem;

public interface ImagemNaAvaliacao {
  public void addImagemAvaliacao(Long avaliacaoId, String imagemBase64) throws IOException;

  public void removeImagem(Long avaliacaoId, Long imagemId)throws IOException;

  public List<Imagem> getImagens(Long avaliacaoId);
}
