package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.io.IOException;
import java.util.List;
import com.br.timn.Gosto.model.Imagem;

public interface ImagemNoEstabelecimento {

  public void addImagemOutro(Long estabelecimentoId, String imagemBase64)throws IOException;

  public void removeImagem(Long estabelecimentoId, Long imagemId);

  public List<Imagem> getImagens(Long estabelecimentoId);

}
