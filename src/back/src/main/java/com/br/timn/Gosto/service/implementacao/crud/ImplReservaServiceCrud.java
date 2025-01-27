package com.br.timn.Gosto.service.implementacao.crud;

import com.br.timn.Gosto.model.type.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.AvaliacaoNotFoundException;
import com.br.timn.Gosto.exception.MesaNotFoundException;
import com.br.timn.Gosto.exception.ReservaNotFoundException;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.Reserva;
import com.br.timn.Gosto.model.get.GetReserva;
import com.br.timn.Gosto.model.update.UpdateReserva;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.repository.MesaRepository;
import com.br.timn.Gosto.repository.ReservaRepository;
import com.br.timn.Gosto.service.interfaces.crud.ReservaServiceCrud;

@Service
public class ImplReservaServiceCrud implements ReservaServiceCrud {

  @Autowired
  private ReservaRepository reservaRepository;

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Autowired
  private MesaRepository mesaRepository;

  @Override
  public Reserva createReserva(Map<String, Object> dados) {

      Long idAvaliador = Long.valueOf(dados.get("idAvaliador").toString());
      Long idEstabelecimento = Long.valueOf(dados.get("idEstabelecimento").toString());
      Long idMesa = Long.valueOf(dados.get("idMesa").toString());
      LocalDateTime dataReserva = LocalDateTime.parse(dados.get("dataReserva").toString());

      Avaliador avaliador = avaliadorRepository.findById(idAvaliador)
          .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliador não encontrado com id: " + idAvaliador));

      Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento)
          .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + idEstabelecimento));

      Mesa mesa = mesaRepository.findById(idMesa)
          .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada com id: " + idMesa));

      boolean conflitoHorario = mesa.getReservasMesa().stream()
      .anyMatch(reserva -> 
          Math.abs(Duration.between(reserva.getDataHoraReserva(), dataReserva).toMinutes()) < 60
      );
      
      if (conflitoHorario) {
        throw new ReservaNotFoundException("ERROR: Já existe uma reserva nesse horário " + dataReserva + " para essa mesa nesse horário.");
      }

      Reserva novaReserva = new Reserva();
      novaReserva.setAvaliador(avaliador);
      novaReserva.setEstabelecimento(estabelecimento);
      novaReserva.setMesaReserva(mesa);
      novaReserva.setStatusReserva(Status.PENDENTE);
      novaReserva.setDataHoraReserva(dataReserva);


      Reserva reservaSalva = reservaRepository.save(novaReserva);
      mesa.getReservasMesa().add(reservaSalva);
      mesaRepository.save(mesa);

      avaliador.getReservasAvaliador().add(reservaSalva);
      avaliadorRepository.save(avaliador);

      estabelecimento.getReservasEstabelecimento().add(reservaSalva);
      estabelecimentoRepository.save(estabelecimento);

      return reservaSalva;

  }

  @Override
  public GetReserva getReservaById(Long id) {
    Reserva reserva = reservaRepository.findById(id)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com ID " + id));
    return new GetReserva(reserva);
  }

  @Override
  public List<GetReserva> getAllReservas() {
    return reservaRepository.findAll().stream()
        .map(reserva -> new GetReserva(reserva))
        .collect(Collectors.toList());
  }

  @Override
  public Reserva updateReserva(UpdateReserva updateReserva) {
    Reserva reserva = reservaRepository.findById(updateReserva.getId())
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com ID " + updateReserva.getId()));
    reserva.setDataHoraReserva(updateReserva.getDataHora());
    reserva.setStatusReserva(Status.parseStatus(updateReserva.getStatusReserva()));
    return reservaRepository.save(reserva);
  }

  @Override
  public void rejeitarReserva(Long idReserva)  {
    Reserva reserva = reservaRepository.findById(idReserva)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com ID " + idReserva));

    reserva.setStatusReserva(Status.REJEITADA);
    reservaRepository.save(reserva);
  }

  @Override
  public void aprovarReserva(Long idReserva)  {
    Reserva reserva = reservaRepository.findById(idReserva)
        .orElseThrow(() -> new ReservaNotFoundException("ERROR: Reserva não encontrada com ID " + idReserva));
    if (reserva.getStatusReserva() == Status.REJEITADA) {
      throw new ReservaNotFoundException(
          "ERROR: Não é possível aprovar uma reserva que já foi rejeitada.");
    }
    reserva.setStatusReserva(Status.APROVADA); 
    reservaRepository.save(reserva); 
  }

}
