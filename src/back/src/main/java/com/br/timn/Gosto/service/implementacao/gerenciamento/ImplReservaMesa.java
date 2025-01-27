package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.exception.MesaNotFoundException;
import com.br.timn.Gosto.exception.ReservaNotFoundException;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.Reserva;
import com.br.timn.Gosto.model.get.GetReserva;
import com.br.timn.Gosto.repository.MesaRepository;
import com.br.timn.Gosto.repository.ReservaRepository;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaMesa;

@Service
public class ImplReservaMesa implements ReservaMesa {

  @Autowired
  private MesaRepository mesaRepository;

  @Autowired
  private ReservaRepository reservaRepository;

  @Override
  public void addReservaMesa(Long mesaId, Long reservaId) {
    Mesa mesa = mesaRepository.findById(mesaId)
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada com id: " + mesaId));
    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));
    boolean existeReservaNoMesmoHorario = mesa.getReservasMesa().stream()
    .anyMatch(r -> {
        long diferencaEmHoras = Math.abs(Duration.between(r.getDataHoraReserva(), reserva.getDataHoraReserva()).toHours());
        return diferencaEmHoras < 1; 
    });


    if (existeReservaNoMesmoHorario) {
      throw new ReservaNotFoundException("ERROR: Já existe uma reserva para a mesa no mesmo horário!");
    }
    mesa.getReservasMesa().add(reserva);
    reserva.setMesaReserva(mesa);
    mesaRepository.save(mesa);
    reservaRepository.save(reserva);
  }

  @Override
  public void removeReserva(Long mesaId, Long reservaId)  {
    Mesa mesa = mesaRepository.findById(mesaId)
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada com id: " + mesaId));
    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));

    if (!mesa.getReservasMesa().contains(reserva)) {
      throw new ReservaNotFoundException("ERROR: Reserva não encontrada na mesa com id: " + mesaId);
    }
    mesa.getReservasMesa().remove(reserva);
    reserva.setMesaReserva(null);
    mesaRepository.save(mesa);
    reservaRepository.save(reserva);
  }

  @Override
  public List<GetReserva> getReservas(Long mesaId) throws MesaNotFoundException {
    Mesa mesa = mesaRepository.findById(mesaId)
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada com id: " + mesaId));
    return mesa.getReservasMesa().stream()
        .map(reserva -> new GetReserva(reserva))
        .collect(Collectors.toList());
  }
}