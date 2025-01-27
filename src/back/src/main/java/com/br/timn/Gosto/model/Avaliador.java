package com.br.timn.Gosto.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.type.Tipo;
import com.br.timn.Gosto.security.SenhaCriptografada;
import com.br.timn.Gosto.util.GeradorImagemPadraoAvaliador;
import com.br.timn.Gosto.util.ImagemUtils;
import com.br.timn.Gosto.validation.validacaoEntidades.ValidacaoAvaliador;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "avaliadores")
public class Avaliador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAvaliador;

  @Column(name = "nome_avaliador")
  private String nomeAvaliador;

  @Column(name = "email_avaliador")
  private String emailAvaliador;

  @Column(name = "telefone_avaliador")
  private String telefoneAvaliador;

  @Column(name = "senha_avaliador")
  private String senhaAvaliador;

  @Column(name = "data_nascimento_avaliador")
  private LocalDate dataNascimentoAvaliador;

  @OneToMany(mappedBy = "avaliadorImagem", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("avaliador-imagens")
  private List<Imagem> imagensAvaliador = new ArrayList<>();

  @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("avaliador-avaliacoes")
  private List<Avaliacao> avaliacoesAvaliador = new ArrayList<>();

  @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("avaliador-reservas")
  private List<Reserva> reservasAvaliador = new ArrayList<>();

  public Long getIdAvaliador() {
    return idAvaliador;
  }

  public void setIdAvaliador(Long idAvaliador) {
    this.idAvaliador = idAvaliador;
  }

  public String getNomeAvaliador() {
    return nomeAvaliador;
  }

  public void setNomeAvaliador(String nomeAvaliador) throws ValidacaoException {
    ValidacaoAvaliador.validarNome(nomeAvaliador);
    this.nomeAvaliador = nomeAvaliador;
  }

  public String getEmailAvaliador() {
    return emailAvaliador;
  }

  public void setEmailAvaliador(String emailAvaliador) throws ValidacaoException {
    ValidacaoAvaliador.validarEmail(emailAvaliador);
    this.emailAvaliador = emailAvaliador;
  }

  public String getTelefoneAvaliador() {
    return telefoneAvaliador;
  }

  public void setTelefoneAvaliador(String telefoneAvaliador) throws ValidacaoException {
    ValidacaoAvaliador.validarTelefone(telefoneAvaliador);
    this.telefoneAvaliador = telefoneAvaliador;
  }

  public String getSenhaAvaliador() {
    return senhaAvaliador;
  }

  public void setSenhaAvaliador(String senhaAvaliador) throws ValidacaoException {
    ValidacaoAvaliador.validarSenha(senhaAvaliador);
    this.senhaAvaliador = SenhaCriptografada.encode(senhaAvaliador);
  }

  public LocalDate getDataNascimentoAvaliador() {
    return dataNascimentoAvaliador;
  }

  public void setDataNascimentoAvaliador(LocalDate dataNascimentoAvaliador) throws ValidacaoException {
    ValidacaoAvaliador.validarDataNascimento(dataNascimentoAvaliador);
    this.dataNascimentoAvaliador = dataNascimentoAvaliador;
  }

  public List<Imagem> getImagensAvaliador() {
    return imagensAvaliador;
  }

  public Imagem getImagemPerfil() {
    return ImagemUtils.getImagemPorTipo(imagensAvaliador, Tipo.PERFIL);
  }

  public Imagem getImagemBanner() {
    return ImagemUtils.getImagemPorTipo(imagensAvaliador, Tipo.BANNER);
  }

  public void setImagemPerfil(Imagem imagem) {
    ImagemUtils.setImagemPorTipo(imagensAvaliador, imagem, Tipo.PERFIL);
  }

  public void setImagemBanner(Imagem imagemBanner) {
    ImagemUtils.setImagemPorTipo(imagensAvaliador, imagemBanner, Tipo.BANNER);
  }

  public void addImagensAvaliador(List<Imagem> imagensAvaliador) {
    this.imagensAvaliador.addAll(imagensAvaliador);
  }

  public List<Avaliacao> getAvaliacoesAvaliador() {
    return avaliacoesAvaliador;
  }

  public void addAvaliacoesAvaliador(List<Avaliacao> avaliacoesAvaliador) {
    this.avaliacoesAvaliador.addAll(avaliacoesAvaliador);
  }

  public void addAvaliacaoAvaliador(Avaliacao avaliacao) {
    this.avaliacoesAvaliador.add(avaliacao);
  }

  public void removeAvaliacaoAvaliador(Avaliacao avaliacao) {
    this.avaliacoesAvaliador.removeIf((Avaliacao a) -> a.getIdAvaliacao().equals(avaliacao.getIdAvaliacao()));
  }

  public List<Reserva> getReservasAvaliador() {
    return reservasAvaliador;
  }

  public void addReservasAvaliador(List<Reserva> reservasAvaliador) {
    this.reservasAvaliador.addAll(reservasAvaliador);
  }

  public void addReservaAvaliador(Reserva reservaAvaliador) {
    this.reservasAvaliador.add(reservaAvaliador);
  }

  public void removeReservaAvaliador(Reserva reservaAvaliador) {
    this.reservasAvaliador.removeIf((Reserva r) -> r.getIdReserva().equals(reservaAvaliador.getIdReserva()));
  }

  public Avaliador(Long idAvaliador, String nomeAvaliador, String emailAvaliador, String telefoneAvaliador,
      String senhaAvaliador, LocalDate dataNascimentoAvaliador, List<Imagem> imagensAvaliador,
      List<Avaliacao> avaliacoesAvaliador, List<Reserva> reservasAvaliador) throws ValidacaoException {
    this.setIdAvaliador(idAvaliador);
    this.setNomeAvaliador(nomeAvaliador);
    this.setEmailAvaliador(emailAvaliador);
    this.setTelefoneAvaliador(telefoneAvaliador);
    this.setSenhaAvaliador(senhaAvaliador);
    this.setDataNascimentoAvaliador(dataNascimentoAvaliador);
    this.addImagensAvaliador(imagensAvaliador);
    this.addAvaliacoesAvaliador(avaliacoesAvaliador);
    this.addReservasAvaliador(reservasAvaliador);
  }

  public Avaliador() {
    geradorImagensPadrao();
  }

  public Avaliador(Long idAvaliador, String nomeAvaliador, String emailAvaliador, String telefoneAvaliador,
      String senhaAvaliador, LocalDate dataNascimentoAvaliador) throws ValidacaoException {
    this.setIdAvaliador(idAvaliador);
    this.setNomeAvaliador(nomeAvaliador);
    this.setEmailAvaliador(emailAvaliador);
    this.setTelefoneAvaliador(telefoneAvaliador);
    this.setSenhaAvaliador(senhaAvaliador);
    this.setDataNascimentoAvaliador(dataNascimentoAvaliador);
    geradorImagensPadrao();
  }

  public void geradorImagensPadrao() {
    this.setImagemPerfil(GeradorImagemPadraoAvaliador.carregarImagemPerfilPadrao(this));
    this.setImagemBanner(GeradorImagemPadraoAvaliador.carregarImagemBannerPadrao(this));
  }
}
