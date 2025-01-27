package com.br.timn.Gosto.validation.validacaoEntidades;

import java.time.LocalDate;

import com.br.timn.Gosto.exception.ValidacaoException;

public class ValidacaoAvaliador extends Validacao {
  public static void validarDataNascimento(LocalDate dataNascimento) throws ValidacaoException {
    if (dataNascimento == null) {
      throw new ValidacaoException("ERROR: Data de nascimento não pode ser vazia.");
    }

    if (dataNascimento.isAfter(LocalDate.now())) {
      throw new ValidacaoException("ERROR: Data de nascimento não pode estar no futuro.");
    }
  }
}
