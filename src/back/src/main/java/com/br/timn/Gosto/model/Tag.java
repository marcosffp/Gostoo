package com.br.timn.Gosto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idTag;

  @Column(name = "nome_tag")
  private String nomeTag;

  @ManyToOne
  @JoinColumn(name = "id_estabelecimento", referencedColumnName = "idEstabelecimento", nullable = true)
  @JsonBackReference("estabelecimento-tags")
  private Estabelecimento estabelecimento;

  public Long getIdTag() {
    return idTag;
  }

  public void setIdTag(Long idTag) {
    this.idTag = idTag;
  }

  public String getNomeTag() {
    return nomeTag;
  }

  public void setNomeTag(String nomeTag) {
    this.nomeTag = nomeTag;
  }

  public Estabelecimento getEstabelecimento() {
    return estabelecimento;
  }

  public void setEstabelecimento(Estabelecimento estabelecimento) {
    this.estabelecimento = estabelecimento;
  }

  public Tag(Long idTag, String nomeTag, Estabelecimento estabelecimento) {
    this.setIdTag(idTag);
    this.setNomeTag(nomeTag);
    this.setEstabelecimento(estabelecimento);
  }

  public Tag() {
  }
}
