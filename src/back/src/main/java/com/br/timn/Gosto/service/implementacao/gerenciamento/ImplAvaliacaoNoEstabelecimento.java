package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.get.GetAvaliacaoEstabelecimento;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.AvaliacaoEstabelecimento;

@Service
public class ImplAvaliacaoNoEstabelecimento implements AvaliacaoEstabelecimento {

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoServiceCrud;

  @Override
  public List<GetAvaliacaoEstabelecimento> getAllAvaliacoes(Long estabelecimentoId) {
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    List<Avaliacao> avaliacoes = estabelecimento.getAvaliacoesEstabelecimento();
    List<GetAvaliacaoEstabelecimento> getAvaliacoes = avaliacoes.stream().map(avaliacao -> new GetAvaliacaoEstabelecimento(avaliacao)).toList();
    return getAvaliacoes;
  }

}
