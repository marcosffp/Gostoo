package com.br.timn.Gosto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesas")
public class Mesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idMesa;

  @Column(name = "nome_mesa", nullable = false)
  private String nomeMesa;

  @Column(name = "descricao_mesa")
  private String descricaoMesa;

  @Column(name = "capacidade_mesa", nullable = false)
  private int capacidadeMesa;

  @ManyToOne
  @JoinColumn(name = "id_estabelecimento", referencedColumnName = "idEstabelecimento", nullable = true)
  @JsonBackReference("estabelecimento-mesas")
  private Estabelecimento estabelecimentoMesa;


    @OneToMany(mappedBy = "mesaReserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("mesa-reservas")
    private List<Reserva> reservasMesa = new ArrayList<>();


  public Estabelecimento getEstabelecimentoMesa() {
    return estabelecimentoMesa;
  }

  public void setEstabelecimentoMesa(Estabelecimento estabelecimentoMesa) {
    this.estabelecimentoMesa = estabelecimentoMesa;
  }

  public List<Reserva> getReservasMesa() {
    return reservasMesa;
  }

  public void addReserva(Reserva reserva) {
    this.reservasMesa.add(reserva);
  }

  public void addReservas(List<Reserva> reservas) {
    this.reservasMesa.addAll(reservas);
  }

  public void removeReserva(Reserva reserva) {
    this.reservasMesa.removeIf(reserva1 -> reserva1.getIdReserva().equals(reserva.getIdReserva()));
  }

  public void removeReservas(List<Reserva> reservas) {
    this.reservasMesa.removeAll(reservas);
  }


  public Long getIdMesa() {
    return idMesa;
  }

  public void setIdMesa(Long idMesa) {
    this.idMesa = idMesa;
  }

  public String getDescricaoMesa() {
    return descricaoMesa;
  }

  public void setDescricaoMesa(String descricaoMesa) {
    this.descricaoMesa = descricaoMesa;
  }

  public int getCapacidadeMesa() {
    return capacidadeMesa;
  }

  public void setCapacidadeMesa(int capacidadeMesa) {
    this.capacidadeMesa = capacidadeMesa;
  }



  public String getNomeMesa() {
    return nomeMesa;
  }

  public void setNomeMesa(String nomeMesa) {
    this.nomeMesa = nomeMesa;
  }

  public Mesa() {
  }

  public Mesa(Long idMesa, String descricaoMesa, int capacidadeMesa, Estabelecimento estabelecimento) {
    this.setIdMesa(idMesa);
    this.setDescricaoMesa(descricaoMesa);
    this.setCapacidadeMesa(capacidadeMesa);
    this.setEstabelecimentoMesa(estabelecimento);
  }

  public Mesa(Long idMesa, String descricaoMesa, int capacidadeMesa, Estabelecimento estabelecimento,List<Reserva> reservas) {
    this.setIdMesa(idMesa);
    this.setDescricaoMesa(descricaoMesa);
    this.setCapacidadeMesa(capacidadeMesa);
    this.setEstabelecimentoMesa(estabelecimento);
    this.addReservas(reservas);
  }

}
