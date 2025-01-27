package com.br.timn.Gosto.model.get;

import java.time.LocalDateTime;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.Reserva;


public class GetReservaAvaliador {
  private Long id;
  private LocalDateTime dataHoraReserva;
  private String statusReserva;
  private Long idAvaliador;
  private Long idEstabelecimento;
  private String nomeEstabelecimento;
  private String emailEstabelecimento;
  private Mesa mesaReserva;


  
  public Mesa getMesaReserva() {
    return mesaReserva;
  }
  public void setMesaReserva(Mesa mesaReserva) {
    this.mesaReserva = mesaReserva;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public LocalDateTime getDataHoraReserva() {
    return dataHoraReserva;
  }
  public void setDataHoraReserva(LocalDateTime dataHoraReserva) {
    this.dataHoraReserva = dataHoraReserva;
  }
  public String getStatusReserva() {
    return statusReserva;
  }
  public void setStatusReserva(String statusReserva) {
    this.statusReserva = statusReserva;
  }
  public Long getIdAvaliador() {
    return idAvaliador;
  }
  public void setIdAvaliador(Long idAvaliador) {
    this.idAvaliador = idAvaliador;
  }
  public Long getIdEstabelecimento() {
    return idEstabelecimento;
  }
  public void setIdEstabelecimento(Long idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }
  public String getNomeEstabelecimento() {
    return nomeEstabelecimento;
  }
  public void setNomeEstabelecimento(String nomeEstabelecimento) {
    this.nomeEstabelecimento = nomeEstabelecimento;
  }
  public String getEmailEstabelecimento() {
    return emailEstabelecimento;
  }

  public void setEmailEstabelecimento(String emailEstabelecimento) {
    this.emailEstabelecimento = emailEstabelecimento;
  }
  
  public GetReservaAvaliador(Reserva reserva) {
    this.id = reserva.getIdReserva();
    this.dataHoraReserva = reserva.getDataHoraReserva();
    this.statusReserva = reserva.getStatusReserva().toString();
    this.idAvaliador = reserva.getAvaliador().getIdAvaliador();
    this.idEstabelecimento = reserva.getEstabelecimento().getIdEstabelecimento();
    this.nomeEstabelecimento = reserva.getEstabelecimento().getNomeEstabelecimento();
    this.emailEstabelecimento = reserva.getEstabelecimento().getEmailEstabelecimento();
    this.mesaReserva = reserva.getMesaReserva();
  }

  public GetReservaAvaliador() {
  }

}
