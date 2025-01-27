package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;

import com.br.timn.Gosto.model.get.GetAvaliacaoEstabelecimento;

public interface AvaliacaoEstabelecimento {
   public List<GetAvaliacaoEstabelecimento> getAllAvaliacoes(Long estabelecimentoId);
}
