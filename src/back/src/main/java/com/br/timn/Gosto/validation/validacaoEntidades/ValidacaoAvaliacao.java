package com.br.timn.Gosto.validation.validacaoEntidades;


import java.time.LocalDate;
import com.br.timn.Gosto.exception.ValidacaoException;

public class ValidacaoAvaliacao{

  private static final double NOTA_MINIMA = 0.0;
  private static final double NOTA_MAXIMA = 5.0;

  public static void validarDescricao(String descricaoAvaliacao) throws ValidacaoException {
    if (descricaoAvaliacao == null || descricaoAvaliacao.trim().isEmpty()) {
      throw new ValidacaoException("ERROR: A descrição da avaliação não pode estar vazia.");
    }
    if (descricaoAvaliacao.length() > 500) {
      throw new ValidacaoException("ERROR: A descrição da avaliação deve ter no máximo 500 caracteres.");
    }
  }

  public static void validarNota(double notaAvaliacao) throws ValidacaoException {
    if (notaAvaliacao<NOTA_MINIMA) {
      throw new ValidacaoException("ERROR: A nota da avaliação não pode ser menor que " + NOTA_MINIMA + ".");
    }
    if (notaAvaliacao>NOTA_MAXIMA) {
      throw new ValidacaoException("ERROR: A nota da avaliação não pode ser maior que " + NOTA_MAXIMA + ".");
    }
  }


  public static void validarData(LocalDate dataAvaliacao) throws ValidacaoException {
    if (dataAvaliacao == null) {
      throw new ValidacaoException("ERROR: A data da avaliação não pode estar vazia.");
    }
  }
}
