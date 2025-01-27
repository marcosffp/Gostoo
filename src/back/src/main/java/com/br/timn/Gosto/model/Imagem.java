package com.br.timn.Gosto.model;

import com.br.timn.Gosto.model.type.Tipo;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "imagens")
public class Imagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idImagem;

  @Column(name = "nome_imagem")
  private String nomeImagem;

  @Lob
  @Column(name = "blob_imagem", columnDefinition = "MEDIUMBLOB")
  private byte[] blobImagem;

  @Column(name = "url_imagem")
  private String urlImagem;

  @ManyToOne
  @JoinColumn(name = "id_estabelecimento", referencedColumnName = "idEstabelecimento", nullable = true)
  @JsonBackReference("estabelecimento-imagens")
  private Estabelecimento estabelecimentoImagem;

  @ManyToOne
  @JoinColumn(name = "id_avaliador", referencedColumnName = "idAvaliador", nullable = true)
  @JsonBackReference("avaliador-imagens")
  private Avaliador avaliadorImagem;

  @ManyToOne
  @JoinColumn(name = "id_avaliacao", referencedColumnName = "idAvaliacao", nullable = true)
  @JsonBackReference("avaliacao-imagens")
  private Avaliacao avaliacaoImagem;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_imagem")
  private Tipo tipoImagem;

  public Long getIdImagem() {
    return idImagem;
  }

  public void setIdImagem(Long idImagem) {
    this.idImagem = idImagem;
  }

  public String getNomeImagem() {
    return nomeImagem;
  }

  public void setNomeImagem(String nomeImagem) {
    this.nomeImagem = nomeImagem;
  }

  public byte[] getBlobImagem() {
    return blobImagem;
  }

  public void setBlobImagem(byte[] blobImagem) {
    this.blobImagem = blobImagem;
  }

  public String getUrlImagem() {
    return urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }

  public Estabelecimento getEstabelecimentoImagem() {
    return estabelecimentoImagem;
  }

  public void setEstabelecimentoImagem(Estabelecimento estabelecimentoImagem) {
    this.estabelecimentoImagem = estabelecimentoImagem;
  }

  public Avaliador getAvaliadorImagem() {
    return avaliadorImagem;
  }

  public void setAvaliadorImagem(Avaliador avaliadorImagem) {
    this.avaliadorImagem = avaliadorImagem;
  }

  public Avaliacao getAvaliacaoImagem() {
    return avaliacaoImagem;
  }

  public void setAvaliacaoImagem(Avaliacao avaliacaoImagem) {
    this.avaliacaoImagem = avaliacaoImagem;
  }

  public Tipo getTipoImagem() {
    return tipoImagem;
  }

  public void setTipoImagem(Tipo tipoImagem) {
    this.tipoImagem = tipoImagem;
  }

  public Imagem() {
  }

  public Imagem(Long idImagem, String nomeImagem, byte[] blobImagem, String urlImagem,
      Estabelecimento estabelecimentoImagem, Avaliador avaliadorImagem, Avaliacao avaliacaoImagem, Tipo tipoImagem) {
    this.setIdImagem(idImagem);
    this.setNomeImagem(nomeImagem);
    this.setBlobImagem(blobImagem);
    this.setUrlImagem(urlImagem);
    this.setEstabelecimentoImagem(estabelecimentoImagem);
    this.setAvaliadorImagem(avaliadorImagem);
    this.setAvaliacaoImagem(avaliacaoImagem);
    this.setTipoImagem(tipoImagem);
  }

}
