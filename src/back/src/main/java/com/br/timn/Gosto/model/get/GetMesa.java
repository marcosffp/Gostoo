package com.br.timn.Gosto.model.get;

import java.util.List;

import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.Reserva;

public class GetMesa {
  private long id;
  private String descricao;
  private int capacidade;
  private String nomeEstabelecimento;
  private String nomeMesa;
  private List<Reserva> reservas;

  public List<Reserva> getReservas() {
    return reservas;
  }

  public void setReservas(List<Reserva> reservas) {
    this.reservas = reservas;
  }


  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public int getCapacidade() {
    return capacidade;
  }
  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }
  public String getNomeEstabelecimento() {
    return nomeEstabelecimento;
  }
  public void setNomeEstabelecimento(String nomeEstabelecimento) {
    this.nomeEstabelecimento = nomeEstabelecimento;
  }

  public GetMesa(Mesa mesa) {
    this.id = mesa.getIdMesa();
    this.descricao = mesa.getDescricaoMesa();
    this.capacidade = mesa.getCapacidadeMesa();
    this.nomeEstabelecimento = mesa.getEstabelecimentoMesa().getNomeEstabelecimento();
    this.nomeMesa = mesa.getNomeMesa();
    this.reservas = mesa.getReservasMesa();
  }

  public GetMesa() {
  }

  public String getNomeMesa() {
    return nomeMesa;
  }

  public void setNomeMesa(String nomeMesa) {
    this.nomeMesa = nomeMesa;
  }
}
