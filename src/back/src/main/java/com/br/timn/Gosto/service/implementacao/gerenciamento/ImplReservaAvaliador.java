package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.exception.AvaliadorNotFoundException;
import com.br.timn.Gosto.exception.ReservaNotFoundException;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Reserva;
import com.br.timn.Gosto.model.get.GetReservaAvaliador;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.ReservaRepository;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaAvaliador;

@Service
public class ImplReservaAvaliador implements ReservaAvaliador {

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Autowired
  private ReservaRepository reservaRepository;

  @Override
  public void addReservaAvaliador(Long reservaId, Long avaliadorId) {
    Avaliador avaliador = avaliadorRepository.findById(avaliadorId)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador não encontrado com id: " + avaliadorId));

    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));

    reserva.setAvaliador(avaliador);
    avaliador.getReservasAvaliador().add(reserva);
    reservaRepository.save(reserva);
  }

  @Override
  public void removeReservaAvaliador(Long reservaId, Long avaliadorId)
       throws AvaliadorNotFoundException, ReservaNotFoundException{
    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));
    Avaliador avaliador = avaliadorRepository.findById(avaliadorId)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador não encontrado com id: " + avaliadorId));

    if (avaliador.getReservasAvaliador().contains(reserva)) {
      avaliador.getReservasAvaliador().remove(reserva);
      reserva.setAvaliador(null); 
      reservaRepository.save(reserva);
    }
  }

  @Override
  public List<GetReservaAvaliador> getReservasAvaliador(Long avaliadorId)
      throws AvaliadorNotFoundException, ReservaNotFoundException {
    Avaliador avaliador = avaliadorRepository.findById(avaliadorId)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador não encontrado com id: " + avaliadorId));

    return avaliador.getReservasAvaliador().stream()
        .map(reserva -> new GetReservaAvaliador(reserva))
        .collect(Collectors.toList());
  }
}
