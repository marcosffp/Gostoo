package com.br.timn.Gosto.service.interfaces.crud;

import java.util.List;
import java.util.Map;
import com.br.timn.Gosto.model.Reserva;

import com.br.timn.Gosto.model.get.GetReserva;

import com.br.timn.Gosto.model.update.UpdateReserva;

public interface ReservaServiceCrud {
  public Reserva createReserva(Map<String, Object> dados);

  public GetReserva getReservaById(Long id);

  public List<GetReserva> getAllReservas();

  public Reserva updateReserva(UpdateReserva updateReserva);

  public void rejeitarReserva(Long idReserva);

  public void aprovarReserva(Long idReserva);
}
