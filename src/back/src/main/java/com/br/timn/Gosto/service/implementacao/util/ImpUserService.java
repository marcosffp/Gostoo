package com.br.timn.Gosto.service.implementacao.util;

import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.service.interfaces.security.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpUserService implements UserService {
  @Autowired private AvaliadorRepository avaliadorRepository;

  @Autowired private EstabelecimentoRepository estabelecimentoRepository;

  @Override
  public Long getUserIdByEmail(String email) {
    Avaliador avaliador = avaliadorRepository.findByEmailAvaliador(email).orElse(null);
    Estabelecimento estabelecimento =
        estabelecimentoRepository.findByEmailEstabelecimento(email).orElse(null);
    if (avaliador == null && estabelecimento == null) {
      throw new RuntimeException("Conta não encontrada");
    }

    return (avaliador == null)
        ? estabelecimento.getIdEstabelecimento()
        : avaliador.getIdAvaliador();
  }

  @Override
  public Integer getUserTypeByEmail(String email) {
    Avaliador avaliador = avaliadorRepository.findByEmailAvaliador(email).orElse(null);
    Estabelecimento estabelecimento =
        estabelecimentoRepository.findByEmailEstabelecimento(email).orElse(null);
    if (avaliador == null && estabelecimento == null) {
      throw new RuntimeException("Conta não encontrada");
    }

    return (avaliador == null) ? 1 : 2;
  }
}
