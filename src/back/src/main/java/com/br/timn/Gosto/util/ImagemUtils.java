package com.br.timn.Gosto.util;

import java.util.List;
import java.util.stream.Collectors;

import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.type.Tipo;

public class ImagemUtils {

  public static Imagem getImagemPorTipo(List<Imagem> imagensEstabelecimento, Tipo tipo) {
    return imagensEstabelecimento.stream()
        .filter(imagem -> imagem.getTipoImagem().equals(tipo))
        .findFirst()
        .orElse(null);
  }

  public static void setImagemPorTipo(List<Imagem> imagensEstabelecimento, Imagem imagem, Tipo tipo) {
    imagem.setTipoImagem(tipo);
    imagensEstabelecimento.removeIf(img -> img.getTipoImagem().equals(tipo));
    imagensEstabelecimento.add(imagem);
  }

  public static List<Imagem> getImagensPorTipo(List<Imagem> imagensEstabelecimento, Tipo tipo) {
    return imagensEstabelecimento.stream()
        .filter(imagem -> imagem.getTipoImagem().equals(tipo))
        .collect(Collectors.toList());
  }

  public static void setImagensOutrosEstabelecimento(List<Imagem> imagensEstabelecimento,
      List<Imagem> novasImagensOutros) {
    int quantidadeAtual = (int) imagensEstabelecimento.stream()
        .filter(imagem -> imagem.getTipoImagem().equals(Tipo.OUTRO))
        .count();

    if (quantidadeAtual + novasImagensOutros.size() > 6) {
      throw new IllegalArgumentException("O estabelecimento só pode ter até 6 imagens do tipo OUTRO.");
    }

    for (Imagem imagem : novasImagensOutros) {
      imagem.setTipoImagem(Tipo.OUTRO);
      imagensEstabelecimento.add(imagem);
    }
  }
}
