package com.br.timn.Gosto.model.get;

import java.time.LocalDateTime;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.Reserva;

public class GetReserva {
  private Long idReserva;
  private Long idEstabelecimento;
  private Long idAvaliador;
  private String nomeAvaliador;
  private String emailAvaliador;
  private String nomeEstabelecimento;
  private String emailEstabelecimento;
  private LocalDateTime dataHoraReserva;
  private Mesa mesaReserva;
  
  public Mesa getMesaReserva() {
    return mesaReserva;
  }

  public void setMesaReserva(Mesa mesaReserva) {
    this.mesaReserva = mesaReserva;
  }

  public Long getIdReserva() {
    return idReserva;
  }
  public void setIdReserva(Long idReserva) {
    this.idReserva = idReserva;
  }
  public Long getIdEstabelecimento() {
    return idEstabelecimento;
  }
  public void setIdEstabelecimento(Long idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }
  public Long getIdAvaliador() {
    return idAvaliador;
  }
  public void setIdAvaliador(Long idAvaliador) {
    this.idAvaliador = idAvaliador;
  }
  public String getNomeAvaliador() {
    return nomeAvaliador;
  }
  public void setNomeAvaliador(String nomeAvaliador) {
    this.nomeAvaliador = nomeAvaliador;
  }
  public String getEmailAvaliador() {
    return emailAvaliador;
  }
  public void setEmailAvaliador(String emailAvaliador) {
    this.emailAvaliador = emailAvaliador;
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
  public LocalDateTime getDataHoraReserva() {
    return dataHoraReserva;
  }
  public void setDataHoraReserva(LocalDateTime dataHoraReserva) {
    this.dataHoraReserva = dataHoraReserva;
  }


  public GetReserva(Reserva reserva) {
    this.idReserva = reserva.getIdReserva();
    this.idEstabelecimento = reserva.getEstabelecimento().getIdEstabelecimento();
    this.idAvaliador = reserva.getAvaliador().getIdAvaliador();
    this.nomeAvaliador = reserva.getAvaliador().getNomeAvaliador();
    this.emailAvaliador = reserva.getAvaliador().getEmailAvaliador();
    this.nomeEstabelecimento = reserva.getEstabelecimento().getNomeEstabelecimento();
    this.emailEstabelecimento = reserva.getEstabelecimento().getEmailEstabelecimento();
    this.dataHoraReserva = reserva.getDataHoraReserva();
    this.mesaReserva = reserva.getMesaReserva();
  }

  public GetReserva() {
  }
}
