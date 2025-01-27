package com.br.timn.Gosto.util;


import com.br.timn.Gosto.model.*;
import com.br.timn.Gosto.model.type.Tipo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GeradorImagemPadraoEstabelecimento {
  private static final String PERFIL_PADRAO_URL = "src/main/resources/static/image/default-profile.png";
  private static final String BANNER_PADRAO_URL = "src/main/resources/static/image/default-banner.jpg";

  public static Imagem carregarImagemPerfilPadrao(Estabelecimento estabelecimento) {
    try {
      byte[] imagemBytes = Files.readAllBytes(Paths.get(PERFIL_PADRAO_URL));
      Imagem imagemPerfil = new Imagem();
      imagemPerfil.setNomeImagem("default-profile.png");
      imagemPerfil.setBlobImagem(imagemBytes);
      imagemPerfil.setUrlImagem(PERFIL_PADRAO_URL);
      imagemPerfil.setEstabelecimentoImagem(estabelecimento);
      imagemPerfil.setTipoImagem(Tipo.PERFIL);

      return imagemPerfil;
    } catch (IOException e) {
      throw new RuntimeException("Erro ao carregar imagem padrão: " + e.getMessage(), e);
    }
  }

  public static Imagem carregarImagemBannerPadrao(Estabelecimento estabelecimento) {
    try {
      byte[] bannerBytes = Files.readAllBytes(Paths.get(BANNER_PADRAO_URL));
      Imagem imagemBanner = new Imagem();
      imagemBanner.setNomeImagem("default-banner.png");
      imagemBanner.setBlobImagem(bannerBytes);
      imagemBanner.setUrlImagem(BANNER_PADRAO_URL);
      imagemBanner.setEstabelecimentoImagem(estabelecimento);
      imagemBanner.setTipoImagem(Tipo.BANNER);
      return imagemBanner;
    } catch (IOException e) {
      throw new RuntimeException("Erro ao carregar imagem padrão: " + e.getMessage(), e);
    }
  }

  public static Imagem carregarImagensOutrosPadrao(Estabelecimento estabelecimento) {
    try {
      byte[] OutroBytes = Files.readAllBytes(Paths.get(BANNER_PADRAO_URL));
      Imagem imagemOutro = new Imagem();
      imagemOutro.setNomeImagem("default-banner.png");
      imagemOutro.setBlobImagem(OutroBytes);
      imagemOutro.setUrlImagem(BANNER_PADRAO_URL);
      imagemOutro.setEstabelecimentoImagem(estabelecimento);
      imagemOutro.setTipoImagem(Tipo.OUTRO);
      return imagemOutro;
    } catch (IOException e) {
      throw new RuntimeException("Erro ao carregar imagem padrão: " + e.getMessage(), e);
    }
  }

  
}
