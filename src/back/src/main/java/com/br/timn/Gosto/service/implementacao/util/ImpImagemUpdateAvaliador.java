package com.br.timn.Gosto.service.implementacao.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.service.interfaces.crud.ImagemServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemUpdateAvaliador;

@Service
public class ImpImagemUpdateAvaliador implements ImagemUpdateAvaliador {

  @Autowired
  private ImagemServiceCrud imagemServiceCrud;

  @Override
  public Imagem updateImagemPerfil(Avaliador avaliador, byte[] imagemPerfil, String novaimagemPadrao)
      throws IOException {
    Imagem imagemPerfilExistente = avaliador.getImagemPerfil();
    if (imagemPerfilExistente != null) {
      imagemPerfilExistente.setBlobImagem(imagemPerfil);
      imagemPerfilExistente.setNomeImagem(novaimagemPadrao);
      imagemPerfilExistente.setAvaliadorImagem(avaliador);
      imagemPerfilExistente.setTipoImagem(Tipo.PERFIL);
      return imagemPerfilExistente;
    } else {
      return imagemServiceCrud.salvarImagem(imagemPerfil, novaimagemPadrao, null, avaliador.getIdAvaliador(),
          null, Tipo.PERFIL);
    }
  }

  @Override
  public Imagem updateImagemBanner(Avaliador avaliador, byte[] imagemBanner, String novaimagemPadrao)
      throws IOException {
    Imagem imagemBannerExistente = avaliador.getImagemBanner();
    if (imagemBannerExistente != null) {
      imagemBannerExistente.setBlobImagem(imagemBanner);
      imagemBannerExistente.setNomeImagem(novaimagemPadrao);
      imagemBannerExistente.setAvaliadorImagem(avaliador);
      imagemBannerExistente.setTipoImagem(Tipo.BANNER);
      return imagemBannerExistente;
    } else {
      return imagemServiceCrud.salvarImagem(imagemBanner, novaimagemPadrao, null, avaliador.getIdAvaliador(),
          null, Tipo.BANNER);
    }
  }

}
