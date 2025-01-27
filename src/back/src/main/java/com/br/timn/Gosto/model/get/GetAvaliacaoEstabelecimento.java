package com.br.timn.Gosto.model.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Imagem;

public class GetAvaliacaoEstabelecimento {
    private String descricao;
  private Long id;
  private double nota;
  private LocalDate dataAvaliacao;
  private String nomeAvaliador;
  private String emailAvaliador;
  private List<Imagem> imagensAvaliacao = new ArrayList<>();
  private Long idAvaliador;
  private Long idEstabelecimento;


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

  public String getEmailAvaliador() {
    return emailAvaliador;
  }

  public void setEmailAvaliador(String emailAvaliador) {
    this.emailAvaliador = emailAvaliador;
  }

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

  public LocalDate getDataAvaliacao() {
    return dataAvaliacao;
  }

  public void setDataAvaliacao(LocalDate dataAvaliacao) {
    this.dataAvaliacao = dataAvaliacao;
  }

  public String getNomeAvaliador() {
    return nomeAvaliador;
  }

  public void setNomeAvaliador(String nomeAvaliador) {
    this.nomeAvaliador = nomeAvaliador;
  }

  public List<Imagem> getImagensAvaliacao() {
    return imagensAvaliacao;
  }

  public void setImagensAvaliacao(List<Imagem> imagensAvaliacao) {
    this.imagensAvaliacao = imagensAvaliacao;
  }

  public GetAvaliacaoEstabelecimento(Avaliacao avaliacao) {
    this.descricao = avaliacao.getDescricaoAvaliacao();
    this.id = avaliacao.getIdAvaliacao();
    this.nota = avaliacao.getNotaAvaliacao();
    this.dataAvaliacao = avaliacao.getDataAvaliacao();
    this.nomeAvaliador = avaliacao.getAvaliador().getNomeAvaliador();
    this.imagensAvaliacao = avaliacao.getImagensAvaliacao();
    this.emailAvaliador = avaliacao.getAvaliador().getEmailAvaliador();
    this.idAvaliador = avaliacao.getAvaliador().getIdAvaliador();
    this.idEstabelecimento = avaliacao.getEstabelecimento().getIdEstabelecimento();
  }

  public GetAvaliacaoEstabelecimento() {
  }

}
