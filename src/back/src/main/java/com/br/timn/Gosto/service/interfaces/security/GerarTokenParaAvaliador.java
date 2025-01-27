package com.br.timn.Gosto.service.interfaces.security;

import com.br.timn.Gosto.model.Avaliador; // Supondo que a entidade Avaliador esteja neste pacote

public interface GerarTokenParaAvaliador {
  String gerarTokenParaAvaliador(Avaliador avaliador, Long id);
}
