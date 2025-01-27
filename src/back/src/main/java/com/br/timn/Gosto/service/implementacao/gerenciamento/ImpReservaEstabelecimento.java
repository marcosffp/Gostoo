package com.br.timn.Gosto.service.implementacao.gerenciamento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.exception.ReservaNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Reserva;
import com.br.timn.Gosto.model.get.GetReservaEstabelecimento;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.repository.ReservaRepository;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaEstabelecimento;

@Service
public class ImpReservaEstabelecimento implements ReservaEstabelecimento {

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private ReservaRepository reservaRepository;

  @Override
  public void addReservaEstabelecimento(Long reservaId, Long estabelecimentoId){
    Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
        .orElseThrow(
            () -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + estabelecimentoId));

    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));
    estabelecimento.getReservasEstabelecimento().add(reserva);
    reserva.setEstabelecimento(estabelecimento);
    reservaRepository.save(reserva);
  }

  @Override
  public void removeReservaEstabelecimento(Long reservaId, Long estabelecimentoId){
    Reserva reserva = reservaRepository.findById(reservaId)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com id: " + reservaId));
    Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
        .orElseThrow(
            () -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + estabelecimentoId));

    if (estabelecimento.getReservasEstabelecimento().contains(reserva)) {
      estabelecimento.getReservasEstabelecimento().remove(reserva);
      reserva.setEstabelecimento(null); 
      reservaRepository.save(reserva);
    }
  }

  @Override
  public List<GetReservaEstabelecimento> getReservasEstabelecimento(Long estabelecimentoId){
    Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
        .orElseThrow(
            () -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + estabelecimentoId));
    return estabelecimento.getReservasEstabelecimento().stream()
        .map(reserva -> new GetReservaEstabelecimento(reserva))
        .collect(Collectors.toList());
  }
}
