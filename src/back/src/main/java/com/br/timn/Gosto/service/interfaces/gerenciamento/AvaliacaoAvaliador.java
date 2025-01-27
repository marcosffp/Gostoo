package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;
import com.br.timn.Gosto.model.get.GetAvaliacaoAvaliador;

public interface AvaliacaoAvaliador {
     public List<GetAvaliacaoAvaliador> getAllAvaliacoes(Long avaliadorId);
}
