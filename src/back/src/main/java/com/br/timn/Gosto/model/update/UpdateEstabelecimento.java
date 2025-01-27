package com.br.timn.Gosto.model.update;

import java.time.LocalDate;

public class UpdateEstabelecimento {
  private Long id;
  private String nome;
  private String localidade;
  private String telefone;
  private String descricao;
  private LocalDate dataCriacao;
  private String charImagemPerfil;
  private String charImagemBanner;
  private String categoria;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getCharImagemPerfil() {
    return charImagemPerfil;
  }

  public void setCharImagemPerfil(String charImagemPerfil) {
    this.charImagemPerfil = charImagemPerfil;
  }

  public String getCharImagemBanner() {
    return charImagemBanner;
  }

  public void setCharImagemBanner(String charImagemBanner) {
    this.charImagemBanner = charImagemBanner;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public UpdateEstabelecimento(Long id, String nome, String localidade, String telefone, String descricao,
      LocalDate dataCriacao, String charImagemPerfil, String charImagemBanner, String categoria) {
    this.id = id;
    this.nome = nome;
    this.localidade = localidade;
    this.telefone = telefone;
    this.descricao = descricao;
    this.dataCriacao = dataCriacao;
    this.charImagemPerfil = charImagemPerfil;
    this.charImagemBanner = charImagemBanner;
    this.categoria = categoria;
  }

  public UpdateEstabelecimento() {
  }
}
