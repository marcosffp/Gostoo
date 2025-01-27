package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;
import com.br.timn.Gosto.model.get.GetReservaAvaliador;

public interface ReservaAvaliador {
    public void addReservaAvaliador(Long reservaId, Long avaliadorId);

    public void removeReservaAvaliador(Long reservaId, Long avaliadorId);

    public List<GetReservaAvaliador> getReservasAvaliador(Long avaliadorId);
}
