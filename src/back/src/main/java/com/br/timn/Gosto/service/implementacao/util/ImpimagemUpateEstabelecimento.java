package com.br.timn.Gosto.service.implementacao.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.service.interfaces.crud.ImagemServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImageUpdateEstabelecimento;

@Service
public class ImpimagemUpateEstabelecimento implements ImageUpdateEstabelecimento {
  @Autowired
  private ImagemServiceCrud imagemServiceCrud;

  @Override
  public Imagem updateImagemPerfil(Estabelecimento estabelecimento, byte[] imagemPerfil, String novaimagemPadrao)
      throws IOException {
    Imagem imagemPerfilExistente = estabelecimento.getImagemPerfil();
    if (imagemPerfilExistente != null) {
      imagemPerfilExistente.setBlobImagem(imagemPerfil);
      imagemPerfilExistente.setNomeImagem(novaimagemPadrao);
      imagemPerfilExistente.setEstabelecimentoImagem(estabelecimento);
      imagemPerfilExistente.setTipoImagem(Tipo.PERFIL);
      return imagemPerfilExistente;
    } else {
      return imagemServiceCrud.salvarImagem(imagemPerfil, novaimagemPadrao, null, null,
          estabelecimento.getIdEstabelecimento(), Tipo.PERFIL);
    }
  }

  @Override
  public List<Imagem> updateImagensOutro(Estabelecimento estabelecimento, List<byte[]> imagensOutro,
      String nome) throws IOException {
    List<Imagem> imagensExistentes = estabelecimento.getImagensOutrosEstabelecimento();
    List<Imagem> imagensAtualizadas = new ArrayList<>();

    for (int i = 0; i < imagensOutro.size(); i++) {
      byte[] novaImagem = imagensOutro.get(i);
      String novoNomeImagem = nome;

      if (i < imagensExistentes.size()) {
        Imagem imagemExistente = imagensExistentes.get(i);
        imagemExistente.setBlobImagem(novaImagem);
        imagemExistente.setNomeImagem(novoNomeImagem);
        imagemExistente.setEstabelecimentoImagem(estabelecimento);
        imagemExistente.setTipoImagem(Tipo.OUTRO);
        imagemServiceCrud.salvarImagem(imagemExistente.getBlobImagem(), imagemExistente.getNomeImagem(),
            null, null, estabelecimento.getIdEstabelecimento(), Tipo.OUTRO);
        imagensAtualizadas.add(imagemExistente);
      }

    }
    estabelecimento.setImagemOutroList(imagensAtualizadas);
    return imagensAtualizadas;
  }

  @Override
  public Imagem updateImagemBanner(Estabelecimento estabelecimento, byte[] imagemBanner, String novaimagemPadrao)
      throws IOException {
    Imagem imagemBannerExistente = estabelecimento.getImagemBanner();
    if (imagemBannerExistente != null) {
      imagemBannerExistente.setBlobImagem(imagemBanner);
      imagemBannerExistente.setNomeImagem(novaimagemPadrao);
      imagemBannerExistente.setEstabelecimentoImagem(estabelecimento);
      imagemBannerExistente.setTipoImagem(Tipo.PERFIL);
      return imagemBannerExistente;
    } else {
      return imagemServiceCrud.salvarImagem(
          imagemBanner, novaimagemPadrao, null, null,
          estabelecimento.getIdEstabelecimento(), Tipo.BANNER);
    }
  }
}
