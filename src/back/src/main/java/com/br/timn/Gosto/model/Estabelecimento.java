package com.br.timn.Gosto.model;

import java.util.*;

import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.security.SenhaCriptografada;
import com.br.timn.Gosto.util.GeradorImagemPadraoEstabelecimento;
import com.br.timn.Gosto.util.ImagemUtils;
import com.br.timn.Gosto.validation.validacaoEntidades.ValidacaoEstabelecimento;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idEstabelecimento;

  @Column(name = "nome_estabelecimento")
  private String nomeEstabelecimento;

  @Column(name = "localidade_estabelecimento")
  private String localidadeEstabelecimento;

  @Column(name = "telefone_estabelecimento")
  private String telefoneEstabelecimento;

  @Column(name = "email_estabelecimento")
  private String emailEstabelecimento;

  @Column(name = "senha_estabelecimento")
  private String senhaEstabelecimento;

  @Column(name = "descricao_estabelecimento")
  private String descricaoEstabelecimento;

  @Column(name = "data_criacao_estabelecimento")
  private LocalDate dataCriacaoEstabelecimento;

  @Column(name = "categoria_estabelecimento")
  private String categoriaEstabelecimento;

  @Column(name = "nota_estabelecimento")
  private Double notaEstabelecimento;

  @OneToMany(mappedBy = "estabelecimentoImagem", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estabelecimento-imagens")
  private List<Imagem> imagensEstabelecimento = new ArrayList<>();

  @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estabelecimento-avaliacoes")
  private List<Avaliacao> avaliacoesEstabelecimento = new ArrayList<>();

  @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estabelecimento-reservas")
  private List<Reserva> reservasEstabelecimento = new ArrayList<>();

  @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estabelecimento-tags")
  private List<Tag> tagsEstabelecimento = new ArrayList<>();

  @OneToMany(mappedBy = "estabelecimentoMesa", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estabelecimento-mesas")
  private List<Mesa> mesasEstabelecimento = new ArrayList<>();

  public Long getIdEstabelecimento() {
    return idEstabelecimento;
  }

  public void setIdEstabelecimento(Long idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }

  public String getNomeEstabelecimento() {
    return nomeEstabelecimento;
  }

  public void setNomeEstabelecimento(String nomeEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarNome(nomeEstabelecimento);
    this.nomeEstabelecimento = nomeEstabelecimento;
  }

  public String getLocalidadeEstabelecimento() {
    return localidadeEstabelecimento;
  }

  public void setLocalidadeEstabelecimento(String localidadeEstabelecimento) {
    this.localidadeEstabelecimento = localidadeEstabelecimento;
  }

  public String getTelefoneEstabelecimento() {
    return telefoneEstabelecimento;
  }

  public void setTelefoneEstabelecimento(String telefoneEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarTelefone(telefoneEstabelecimento);
    this.telefoneEstabelecimento = telefoneEstabelecimento;
  }

  public String getEmailEstabelecimento() {
    return emailEstabelecimento;
  }

  public void setEmailEstabelecimento(String emailEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarEmail(emailEstabelecimento);
    this.emailEstabelecimento = emailEstabelecimento;
  }

  public String getSenhaEstabelecimento() {
    return senhaEstabelecimento;
  }

  public void setSenhaEstabelecimento(String senhaEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarSenha(senhaEstabelecimento);
    this.senhaEstabelecimento = SenhaCriptografada.encode(senhaEstabelecimento);
  }

  public String getDescricaoEstabelecimento() {
    return descricaoEstabelecimento;
  }

  public void setDescricaoEstabelecimento(String descricaoEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarDescricao(descricaoEstabelecimento);
    this.descricaoEstabelecimento = descricaoEstabelecimento;
  }

  public LocalDate getDataCriacaoEstabelecimento() {
    return dataCriacaoEstabelecimento;
  }

  public void setDataCriacaoEstabelecimento(LocalDate dataCriacaoEstabelecimento) throws ValidacaoException {
    ValidacaoEstabelecimento.validarDataCriacaoEstabelecimento(dataCriacaoEstabelecimento);
    this.dataCriacaoEstabelecimento = dataCriacaoEstabelecimento;
  }

  public String getCategoriaEstabelecimento() {
    return categoriaEstabelecimento;
  }

  public void setCategoriaEstabelecimento(String categoriaEstabelecimento) {
    this.categoriaEstabelecimento = categoriaEstabelecimento;
  }

  public Double getNotaEstabelecimento() {
    return notaEstabelecimento;
  }

  public void setNotaEstabelecimento(Double notaEstabelecimento) {
    this.notaEstabelecimento = notaEstabelecimento;
  }

  public List<Imagem> getImagensEstabelecimento() {
    return imagensEstabelecimento;
  }

  public void addImagensEstabelecimento(List<Imagem> imagensEstabelecimento) {
    this.imagensEstabelecimento.addAll(imagensEstabelecimento);
  }

  public List<Avaliacao> getAvaliacoesEstabelecimento() {
    return avaliacoesEstabelecimento;
  }

  public void addAvaliacoesEstabelecimento(List<Avaliacao> avaliacoesEstabelecimento) {
    this.avaliacoesEstabelecimento.addAll(avaliacoesEstabelecimento);
  }

  public void addAvaliacaoEstabelecimento(Avaliacao avaliacao) {
    this.avaliacoesEstabelecimento.add(avaliacao);
  }

  public void removeAvaliacaoEstabelecimento(Avaliacao avaliacao) {
    this.avaliacoesEstabelecimento.removeIf((Avaliacao a) -> a.getIdAvaliacao().equals(avaliacao.getIdAvaliacao()));
  }

  public List<Reserva> getReservasEstabelecimento() {
    return reservasEstabelecimento;
  }

  public void addReservasEstabelecimento(List<Reserva> reservasEstabelecimento) {
    this.reservasEstabelecimento.addAll(reservasEstabelecimento);
  }

  public void addReservaEstabelecimento(Reserva reserva) {
    this.reservasEstabelecimento.add(reserva);
  }

  public void removeReservaEstabelecimento(Reserva reserva) {
    this.reservasEstabelecimento.removeIf((Reserva r) -> r.getIdReserva().equals(reserva.getIdReserva()));
  }

  public List<Tag> getTagsEstabelecimento() {
    return tagsEstabelecimento;
  }

  public void addTagsEstabelecimento(List<Tag> tagsEstabelecimento) {
    this.tagsEstabelecimento.addAll(tagsEstabelecimento);
  }

  public List<Mesa> getMesasEstabelecimento() {
    return mesasEstabelecimento;
  }

  public void addMesaEstabelecimento(Mesa mesa) {
    this.mesasEstabelecimento.add(mesa);
  }

  public void addMesasEstabelecimento(List<Mesa> mesasEstabelecimento) {
    this.mesasEstabelecimento.addAll(mesasEstabelecimento);
  }

  public void removeMesaEstabelecimento(Mesa mesa) {
    this.mesasEstabelecimento.removeIf((Mesa m) -> m.getIdMesa().equals(mesa.getIdMesa()));
  }

  public void removeMesasEstabelecimento(List<Mesa> mesas) {
    this.mesasEstabelecimento.removeAll(mesas);
  }

  public Imagem getImagemPerfil() {
    return ImagemUtils.getImagemPorTipo(imagensEstabelecimento, Tipo.PERFIL);
  }

  public Imagem getImagemBanner() {
    return ImagemUtils.getImagemPorTipo(imagensEstabelecimento, Tipo.BANNER);
  }

  public void setImagemPerfil(Imagem imagem) {
    ImagemUtils.setImagemPorTipo(imagensEstabelecimento, imagem, Tipo.PERFIL);
  }

  public void setImagemBanner(Imagem imagemBanner) {
    ImagemUtils.setImagemPorTipo(imagensEstabelecimento, imagemBanner, Tipo.BANNER);
  }

  public Imagem getImagemOutro() {
    return ImagemUtils.getImagemPorTipo(imagensEstabelecimento, Tipo.OUTRO);
  }

  public void setImagemOutro(Imagem imagemOutro) {
    ImagemUtils.setImagemPorTipo(imagensEstabelecimento, imagemOutro, Tipo.OUTRO);
  }

  public List<Imagem> getImagensOutrosEstabelecimento() {
    return ImagemUtils.getImagensPorTipo(imagensEstabelecimento, Tipo.OUTRO);
  }

  public void setImagemOutroList(List<Imagem> imagensOutrosEstabelecimento) {
    ImagemUtils.setImagensOutrosEstabelecimento(imagensEstabelecimento, imagensOutrosEstabelecimento);
  }

  public Estabelecimento(Long idEstabelecimento, String nomeEstabelecimento, String localidadeEstabelecimento,
      String telefoneEstabelecimento, String emailEstabelecimento, String senhaEstabelecimento,
      String descricaoEstabelecimento, LocalDate dataCriacaoEstabelecimento, String categoriaEstabelecimento,
      Double notaEstabelecimento, List<Imagem> imagensEstabelecimento, List<Avaliacao> avaliacoesEstabelecimento,
      List<Reserva> reservasEstabelecimento, List<Tag> tagsEstabelecimento, List<Mesa> mesasEstabelecimento)
      throws ValidacaoException {
    this.setIdEstabelecimento(idEstabelecimento);
    this.setNomeEstabelecimento(nomeEstabelecimento);
    this.setLocalidadeEstabelecimento(localidadeEstabelecimento);
    this.setTelefoneEstabelecimento(telefoneEstabelecimento);
    this.setEmailEstabelecimento(emailEstabelecimento);
    this.setSenhaEstabelecimento(senhaEstabelecimento);
    this.setDescricaoEstabelecimento(descricaoEstabelecimento);
    this.setDataCriacaoEstabelecimento(dataCriacaoEstabelecimento);
    this.setCategoriaEstabelecimento(categoriaEstabelecimento);
    this.setNotaEstabelecimento(notaEstabelecimento);
    this.addImagensEstabelecimento(imagensEstabelecimento);
    this.addAvaliacoesEstabelecimento(avaliacoesEstabelecimento);
    this.addReservasEstabelecimento(reservasEstabelecimento);
    this.addTagsEstabelecimento(tagsEstabelecimento);
    this.addMesasEstabelecimento(mesasEstabelecimento);
  }

  public Estabelecimento() {
  }

  public Estabelecimento(Long idEstabelecimento, String nomeEstabelecimento, String localidadeEstabelecimento,
      String telefoneEstabelecimento, String emailEstabelecimento, String senhaEstabelecimento,
      String descricaoEstabelecimento, LocalDate dataCriacaoEstabelecimento, String categoriaEstabelecimento,
      List<Mesa> mesasEstabelecimento, boolean gerarImagensPadrao) throws ValidacaoException {
    this.setIdEstabelecimento(idEstabelecimento);
    this.setNomeEstabelecimento(nomeEstabelecimento);
    this.setLocalidadeEstabelecimento(localidadeEstabelecimento);
    this.setTelefoneEstabelecimento(telefoneEstabelecimento);
    this.setEmailEstabelecimento(emailEstabelecimento);
    this.setSenhaEstabelecimento(senhaEstabelecimento);
    this.setDescricaoEstabelecimento(descricaoEstabelecimento);
    this.setDataCriacaoEstabelecimento(dataCriacaoEstabelecimento);
    this.setCategoriaEstabelecimento(categoriaEstabelecimento);
    this.addMesasEstabelecimento(mesasEstabelecimento);
    geradorImagensPadrao();
  }

  public void geradorImagensPadrao() {
    this.setImagemPerfil(GeradorImagemPadraoEstabelecimento.carregarImagemPerfilPadrao(this));
    this.setImagemBanner(GeradorImagemPadraoEstabelecimento.carregarImagemBannerPadrao(this));
    this.setImagemOutro(GeradorImagemPadraoEstabelecimento.carregarImagensOutrosPadrao(this));
  }

}
