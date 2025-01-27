package com.br.timn.Gosto.model.update;

import java.time.LocalDate;

public class UpdateAvaliacao {
  private String descricao;
  private Long id;
  private double nota;
  private LocalDate data;
  
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public double getNota() {
    return nota;
  }
  public void setNota(double nota) {
    this.nota = nota;
  }
  public LocalDate getData() {
    return data;
    }

    public void setData(LocalDate data) {
    this.data = data;
    }
    
    public UpdateAvaliacao(String descricao, Long id, double nota, LocalDate data) {
    this.descricao = descricao;
    this.id = id;
    this.nota = nota;
    this.data = data;
  }

  
}
