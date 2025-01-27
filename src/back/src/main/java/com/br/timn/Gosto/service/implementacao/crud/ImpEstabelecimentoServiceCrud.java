package com.br.timn.Gosto.service.implementacao.crud;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.update.UpdateEstabelecimento;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImageUpdateEstabelecimento;
import com.br.timn.Gosto.util.ConvertorChar256;
import com.br.timn.Gosto.util.GeradorImagemPadraoEstabelecimento;
import com.br.timn.Gosto.validation.ValidacaoEmailUnico;

@Service
public class ImpEstabelecimentoServiceCrud implements EstabelecimentoServiceCrud {

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Autowired
  private ImageUpdateEstabelecimento imagemUpateEstabelecimento;

  public Estabelecimento createEstabelecimento(Estabelecimento estabelecimento) throws Exception{
    ValidacaoEmailUnico.validarEmailUnicoEstabelecimento(estabelecimento.getEmailEstabelecimento(),
        estabelecimentoRepository);
    ValidacaoEmailUnico.validarEmailUnicoAvaliador(estabelecimento.getEmailEstabelecimento(), avaliadorRepository);
    if (estabelecimento.getImagemPerfil() == null) {
      Imagem imagemPerfilPadrao = GeradorImagemPadraoEstabelecimento.carregarImagemPerfilPadrao(estabelecimento);
      imagemPerfilPadrao.setEstabelecimentoImagem(estabelecimento);
      estabelecimento.setImagemPerfil(imagemPerfilPadrao);
    }
    if (estabelecimento.getImagemBanner() == null) {
      Imagem imagemBannerPadrao = GeradorImagemPadraoEstabelecimento.carregarImagemBannerPadrao(estabelecimento);
      imagemBannerPadrao.setEstabelecimentoImagem(estabelecimento);
      estabelecimento.setImagemBanner(imagemBannerPadrao);
    }
    Estabelecimento createdEstabelecimento = estabelecimentoRepository.save(estabelecimento);
    return createdEstabelecimento;
  }

  @Override
  public Estabelecimento getEstabelecimentoById(Long id) {
    return estabelecimentoRepository.findById(id)
        .orElseThrow(() -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento com ID " + id + " não encontrado."));
  }

  @Override
  public List<Estabelecimento> getAllEstabelecimentos() {
    return estabelecimentoRepository.findAll();
  }

  @Override
  public void deleteEstabelecimento(Long id) {
    Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
        .orElseThrow(() -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento com ID " + id + " não encontrado."));
    estabelecimentoRepository.delete(estabelecimento);
  }

  @Override
  public Estabelecimento updateEstabelecimento(UpdateEstabelecimento update) throws Exception,IOException {
    Estabelecimento estabelecimento = estabelecimentoRepository.findById(update.getId())
        .orElseThrow(() -> new EstabelecimentoNotFoundException(
            "Estabelecimento com ID " + update.getId() + " não encontrado."));
    estabelecimento.setNomeEstabelecimento(update.getNome());
    estabelecimento.setLocalidadeEstabelecimento(update.getLocalidade());
    estabelecimento.setTelefoneEstabelecimento(update.getTelefone());
    estabelecimento.setDescricaoEstabelecimento(update.getDescricao());
    estabelecimento.setDataCriacaoEstabelecimento(update.getDataCriacao());
    estabelecimento.setCategoriaEstabelecimento(update.getCategoria());
    String imagemCharPerfil = update.getCharImagemPerfil();
    String imagemCharBanner = update.getCharImagemBanner();
    byte[] imagemBytesPerfil = ConvertorChar256.convertChar256ToBytes(imagemCharPerfil);
    byte[] imagemBytesBanner = ConvertorChar256.convertChar256ToBytes(imagemCharBanner);
      estabelecimento
          .setImagemPerfil(imagemUpateEstabelecimento.updateImagemPerfil(estabelecimento, imagemBytesPerfil, "perfil"));
      estabelecimento
          .setImagemBanner(imagemUpateEstabelecimento.updateImagemBanner(estabelecimento, imagemBytesBanner, "banner"));
    return estabelecimentoRepository.save(estabelecimento);
  }

  @Override
  public Estabelecimento getEstabelecimentoByEmail(String email) {
    return estabelecimentoRepository.findByEmailEstabelecimento(email)
        .orElseThrow(
            () -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento com email " + email + " não encontrado."));
  }

}
