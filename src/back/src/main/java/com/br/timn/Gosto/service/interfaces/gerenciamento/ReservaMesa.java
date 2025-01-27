package com.br.timn.Gosto.service.interfaces.gerenciamento;

import java.util.List;
import com.br.timn.Gosto.model.get.GetReserva;

public interface ReservaMesa {
  public void addReservaMesa(Long mesaId, Long reservaId);

    public void removeReserva(Long mesaId, Long reservaId);

  public List<GetReserva> getReservas(Long mesaId);
}
