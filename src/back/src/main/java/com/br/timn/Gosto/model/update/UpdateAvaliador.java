package com.br.timn.Gosto.model.update;

import java.time.LocalDate;

public class UpdateAvaliador {

  private Long id;
  private String nome;
  private String telefone;
  private LocalDate dataNascimento;
  private String charImagemPerfil;
  private String charImagemBanner;

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

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
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

  public UpdateAvaliador() {
  }

  public UpdateAvaliador(Long id, String nome, String telefone, LocalDate dataNascimento,
      String charImagemPerfil,
      String charImagemBanner) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
    this.charImagemPerfil = charImagemPerfil;
    this.charImagemBanner = charImagemBanner;
  }
}
