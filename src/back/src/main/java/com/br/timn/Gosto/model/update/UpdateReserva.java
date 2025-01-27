package com.br.timn.Gosto.model.update;

import java.time.LocalDateTime;

public class UpdateReserva {
  private Long id;
  private LocalDateTime dataHora;
  private String statusReserva;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public LocalDateTime getDataHora() {
    return dataHora;
  }
  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }
  public String getStatusReserva() {
    return statusReserva;
  }
  public void setStatusReserva(String statusReserva) {
    this.statusReserva = statusReserva;
  }

  public UpdateReserva(Long id, LocalDateTime dataHora, String statusReserva) {
    this.id = id;
    this.dataHora = dataHora;
    this.statusReserva = statusReserva;
  }

  public UpdateReserva() {
  }
  
}
