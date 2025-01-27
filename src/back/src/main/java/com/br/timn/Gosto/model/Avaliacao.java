package com.br.timn.Gosto.model;

import java.time.LocalDate;
import java.util.*;

import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.validation.validacaoEntidades.ValidacaoAvaliacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAvaliacao;

  @Column(name = "descricao_avaliacao")
  private String descricaoAvaliacao;

  @Column(name = "nota_avaliacao")
  private double notaAvaliacao;

  @Column(name = "data_avaliacao")
  private LocalDate dataAvaliacao;

  @ManyToOne
  @JoinColumn(name = "id_avaliador", referencedColumnName = "idAvaliador", nullable = true)
  @JsonBackReference("avaliador-avaliacoes")
  private Avaliador avaliador;

  @ManyToOne
  @JoinColumn(name = "id_estabelecimento", referencedColumnName = "idEstabelecimento", nullable = true)
  @JsonBackReference("estabelecimento-avaliacoes")
  private Estabelecimento estabelecimento;

  @OneToMany(mappedBy = "avaliacaoImagem", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("avaliacao-imagens")
  private List<Imagem> imagensAvaliacao = new ArrayList<>();

  public Long getIdAvaliacao() {
    return idAvaliacao;
  }

  public void setIdAvaliacao(Long idAvaliacao) {
    this.idAvaliacao = idAvaliacao;
  }

  public String getDescricaoAvaliacao() {
    return descricaoAvaliacao;
  }

  public void setDescricaoAvaliacao(String descricaoAvaliacao) throws ValidacaoException{
    ValidacaoAvaliacao.validarDescricao(descricaoAvaliacao);
    this.descricaoAvaliacao = descricaoAvaliacao;
  }

  public double getNotaAvaliacao() {
    return notaAvaliacao;
  }

  public void setNotaAvaliacao(double notaAvaliacao) throws ValidacaoException{
    ValidacaoAvaliacao.validarNota(notaAvaliacao);
    this.notaAvaliacao = notaAvaliacao;
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

  public List<Imagem> getImagensAvaliacao() {
    return imagensAvaliacao;
  }

  public void addImagensAvaliacao(List<Imagem> imagensAvaliacao) {
    this.imagensAvaliacao.addAll(imagensAvaliacao);
  }

  public void addImagemAvaliacao(Imagem imagemAvaliacao) {
    imagemAvaliacao.setTipoImagem(Tipo.OUTRO);
    this.imagensAvaliacao.add(imagemAvaliacao);
  }

  public LocalDate getDataAvaliacao() {
    return dataAvaliacao;
  }

  public void setDataAvaliacao(LocalDate dataAvaliacao) throws ValidacaoException{
    ValidacaoAvaliacao.validarData(dataAvaliacao);
    this.dataAvaliacao = dataAvaliacao;
  }

  public Avaliacao(Long idAvaliacao, String descricaoAvaliacao, double notaAvaliacao, Avaliador avaliador,
      Estabelecimento estabelecimento, LocalDate dataAvaliacao, List<Imagem> imagensAvaliacao) throws ValidacaoException{
    this.setIdAvaliacao(idAvaliacao);
    this.setDescricaoAvaliacao(descricaoAvaliacao);
    this.setNotaAvaliacao(notaAvaliacao);
    this.setAvaliador(avaliador);
    this.setEstabelecimento(estabelecimento);
    this.setDataAvaliacao(dataAvaliacao);
    this.addImagensAvaliacao(imagensAvaliacao);
  }

  public Avaliacao(Long idAvaliacao, String descricaoAvaliacao, double notaAvaliacao, Avaliador avaliador,
      Estabelecimento estabelecimento, LocalDate dataAvaliacao) throws ValidacaoException{
    this.setIdAvaliacao(idAvaliacao);
    this.setDescricaoAvaliacao(descricaoAvaliacao);
    this.setNotaAvaliacao(notaAvaliacao);
    this.setAvaliador(avaliador);
    this.setEstabelecimento(estabelecimento);
    this.setDataAvaliacao(dataAvaliacao);
  }

  public Avaliacao() {
  }
}
