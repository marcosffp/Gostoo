package com.br.timn.Gosto.validation;

import com.br.timn.Gosto.exception.AvaliadorNotFoundException;
import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;

public class ValidacaoEmailUnico {
  private ValidacaoEmailUnico() {
  }
  
  public static void validarEmailUnicoAvaliador(String email, AvaliadorRepository avaliadorRepository) throws AvaliadorNotFoundException {
    if (avaliadorRepository.findByEmailAvaliador(email).isPresent()) {
      throw new AvaliadorNotFoundException("ERROR: E-mail " + email + " já está em uso em Conta Avaliador.");
    }
  }

  public static void validarEmailUnicoEstabelecimento(String email, EstabelecimentoRepository estabelecimentoRepository) throws EstabelecimentoNotFoundException {
    if (estabelecimentoRepository.findByEmailEstabelecimento(email).isPresent()) {
      throw new EstabelecimentoNotFoundException("ERROR: E-mail " + email + " já está em uso em Conta Estabelecimento.");
    }
  }
}
