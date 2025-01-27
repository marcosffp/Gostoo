package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;
import com.br.timn.Gosto.model.get.GetReservaEstabelecimento;

public interface ReservaEstabelecimento {
  public void addReservaEstabelecimento(Long reservaId, Long estabelecimentoId);

  public void removeReservaEstabelecimento(Long reservaId, Long estabelecimentoId);

  public List<GetReservaEstabelecimento> getReservasEstabelecimento(Long estabelecimentoId);
}
