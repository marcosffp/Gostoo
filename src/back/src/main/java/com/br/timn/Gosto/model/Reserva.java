package com.br.timn.Gosto.model;

import java.time.LocalDateTime;
import com.br.timn.Gosto.model.type.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idReserva;

  @Column(name = "datahora_reserva", nullable = false)
  private LocalDateTime dataHoraReserva;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_reserva", nullable = false)
  private Status statusReserva;

  @ManyToOne
  @JoinColumn(name = "id_avaliador", referencedColumnName = "idAvaliador", nullable = false)
  @JsonBackReference("avaliador-reservas")
  private Avaliador avaliador;

  @ManyToOne
  @JoinColumn(name = "id_estabelecimento", referencedColumnName = "idEstabelecimento", nullable = false)
  @JsonBackReference("estabelecimento-reservas")
  private Estabelecimento estabelecimento;

  @ManyToOne
  @JoinColumn(name = "id_mesa", referencedColumnName = "idMesa", nullable = true)
  @JsonBackReference("mesa-reservas")
  private Mesa mesaReserva;

  public Long getIdReserva() {
    return idReserva;
  }

  public void setIdReserva(Long idReserva) {
    this.idReserva = idReserva;
  }

  public LocalDateTime getDataHoraReserva() {
    return dataHoraReserva;
  }

  public void setDataHoraReserva(LocalDateTime dataHoraReserva) {
    this.dataHoraReserva = dataHoraReserva;
  }

  public Status getStatusReserva() {
    return statusReserva;
  }

  public void setStatusReserva(Status statusReserva) {
    this.statusReserva = statusReserva;
  }

  public Avaliador getAvaliador() {
    return avaliador;
  }

  public void setAvaliador(Avaliador avaliador) {
    this.avaliador = avaliador;
  }

  public Estabelecimento getEstabelecimento() {
    return estabelecimento;
  }

  public void setEstabelecimento(Estabelecimento estabelecimento) {
    this.estabelecimento = estabelecimento;
  }

  public Mesa getMesaReserva() {
    return mesaReserva;
  }

  public void setMesaReserva(Mesa mesaReserva) {
    this.mesaReserva = mesaReserva;
  }


  public Reserva() {
  }

  public Reserva(Long idReserva, LocalDateTime dataHoraReserva, Status statusReserva, Avaliador avaliador,
      Estabelecimento estabelecimento, Mesa mesasReserva) {
    this.setIdReserva(idReserva);
    this.setDataHoraReserva(dataHoraReserva);
    this.setStatusReserva(statusReserva);
    this.setAvaliador(avaliador);
    this.setEstabelecimento(estabelecimento);
    this.setMesaReserva(mesasReserva);
  }

  public Reserva(Long idReserva, LocalDateTime dataHoraReserva, Status statusReserva, Avaliador avaliador,
      Estabelecimento estabelecimento) {
    this.setIdReserva(idReserva);
    this.setDataHoraReserva(dataHoraReserva);
    this.setStatusReserva(statusReserva);
    this.setAvaliador(avaliador);
    this.setEstabelecimento(estabelecimento);
  }

}
