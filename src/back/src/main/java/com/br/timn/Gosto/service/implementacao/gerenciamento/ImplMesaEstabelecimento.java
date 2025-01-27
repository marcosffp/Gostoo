package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.exception.MesaNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.get.GetMesaEstabelecimento;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.MesaEstabelecimento;

@Service
public class ImplMesaEstabelecimento implements MesaEstabelecimento {

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoServiceCrud;

  @Override
  public List<GetMesaEstabelecimento> getAllMesa(Long estabelecimentoId){
    Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoById(estabelecimentoId);
    if (estabelecimento == null) {
      throw new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + estabelecimentoId);
    }
    List<Mesa> mesas = estabelecimento.getMesasEstabelecimento();
    if (mesas.isEmpty()) {
      throw new MesaNotFoundException("ERROR: Nenhuma mesa encontrada para o estabelecimento com id: " + estabelecimentoId);
    }
    List<GetMesaEstabelecimento> getMesas = mesas.stream().map(mesa -> new GetMesaEstabelecimento(mesa)).collect(Collectors.toList());
    return getMesas;
  }

}
