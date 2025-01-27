package com.br.timn.Gosto.validation.validacaoEntidades;

import java.time.LocalDate;

import com.br.timn.Gosto.exception.ValidacaoException;

public class ValidacaoEstabelecimento extends Validacao {

  public static void validarDescricao(String descricao) throws ValidacaoException {
    int maxLength = 255;

    if (descricao == null || descricao.trim().isEmpty()) {
      throw new IllegalArgumentException("ERROR: A descrição do estabelecimento não pode ser vazia.");
    }

    if (descricao.length() > maxLength) {
      throw new ValidacaoException("ERROR: A descrição do estabelecimento não pode exceder " + maxLength + " caracteres.");
    }
  }

  public static void validarDataCriacaoEstabelecimento(LocalDate dataCriacaoEstabelecimento) throws ValidacaoException {
    if (dataCriacaoEstabelecimento == null) {
      throw new ValidacaoException("ERROR: A data de criação do estabelecimento não pode ser vazia.");
    }
    if (dataCriacaoEstabelecimento.isAfter(LocalDate.now())) {
      throw new ValidacaoException("ERROR: A data de criação do estabelecimento não pode estar no futuro.");
    }
  }

}
