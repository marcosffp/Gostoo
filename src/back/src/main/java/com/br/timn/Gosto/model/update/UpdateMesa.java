package com.br.timn.Gosto.model.update;

public class UpdateMesa {
  private long id;
  private String nome;
  private String descricao;
  private int capacidade;
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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

  public UpdateMesa(long id, String nome, String descricao, int capacidade) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.capacidade = capacidade;
  }

  public UpdateMesa() {
  }
  
}
