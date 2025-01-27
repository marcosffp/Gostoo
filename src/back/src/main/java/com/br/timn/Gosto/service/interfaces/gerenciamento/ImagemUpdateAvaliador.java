package com.br.timn.Gosto.service.interfaces.gerenciamento;



import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Imagem;
import java.io.IOException;

public interface ImagemUpdateAvaliador {
  public Imagem updateImagemPerfil(Avaliador avaliador, byte[] imagemPerfil, String novaimagemPadrao)
 throws IOException;

  public Imagem updateImagemBanner(Avaliador avaliador, byte[] imagemBanner,
      String novaimagemPadrao) throws IOException;
}
