package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.get.GetAvaliacaoAvaliador;
import com.br.timn.Gosto.service.interfaces.crud.AvaliadorServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.AvaliacaoAvaliador;

@Service
public class ImplAvaliacaoAvaliador implements AvaliacaoAvaliador {
  @Autowired
  private AvaliadorServiceCrud avaliadorServiceCrud;
  @Override
  public List<GetAvaliacaoAvaliador> getAllAvaliacoes(Long avaliadorId){
    Avaliador avaliador = avaliadorServiceCrud.getAvaliadorById(avaliadorId);
    List<Avaliacao> avaliacoes = avaliador.getAvaliacoesAvaliador();
    List<GetAvaliacaoAvaliador> getAvaliacoes = avaliacoes.stream().map(avaliacao -> new GetAvaliacaoAvaliador(avaliacao)).toList();
    return getAvaliacoes;
  }
  
}
