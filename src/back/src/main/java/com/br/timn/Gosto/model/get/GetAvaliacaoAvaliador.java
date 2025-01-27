package com.br.timn.Gosto.model.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Imagem;

public class GetAvaliacaoAvaliador {
  private String descricao;
  private Long id;
  private double nota;
  private LocalDate dataAvaliacao;
  private String nomeEstabelecimento;
  private String emailEstabelecimento;
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


  public String getNomeEstabelecimento() {
    return nomeEstabelecimento;
  }

  public void setNomeEstabelecimento(String nomeEstabelecimento) {
    this.nomeEstabelecimento = nomeEstabelecimento;
  }

  public List<Imagem> getImagensAvaliacao() {
    return imagensAvaliacao;
  }

  public void setImagensAvaliacao(List<Imagem> imagensAvaliacao) {
    this.imagensAvaliacao = imagensAvaliacao;
  }

  public GetAvaliacaoAvaliador(Avaliacao avaliacao) {
    this.descricao = avaliacao.getDescricaoAvaliacao();
    this.id = avaliacao.getIdAvaliacao();
    this.nota = avaliacao.getNotaAvaliacao();
    this.dataAvaliacao = avaliacao.getDataAvaliacao();
    this.nomeEstabelecimento = avaliacao.getEstabelecimento().getNomeEstabelecimento();
    this.imagensAvaliacao = avaliacao.getImagensAvaliacao();
    this.emailEstabelecimento = avaliacao.getEstabelecimento().getEmailEstabelecimento();
    this.idAvaliador = avaliacao.getAvaliador().getIdAvaliador();
    this.idEstabelecimento = avaliacao.getEstabelecimento().getIdEstabelecimento();
  }

  public GetAvaliacaoAvaliador() {
  }

  public String getEmailEstabelecimento() {
    return emailEstabelecimento;
  }

  public void setEmailEstabelecimento(String emailEstabelecimento) {
    this.emailEstabelecimento = emailEstabelecimento;
  }

}
