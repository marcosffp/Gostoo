package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;
import com.br.timn.Gosto.model.get.GetMesaEstabelecimento;

public interface MesaEstabelecimento {
     public List<GetMesaEstabelecimento> getAllMesa(Long estabelecimentoId);
}
