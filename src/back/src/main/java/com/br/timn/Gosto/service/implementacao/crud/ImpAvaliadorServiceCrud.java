package com.br.timn.Gosto.service.implementacao.crud;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.AvaliadorNotFoundException;
import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.update.UpdateAvaliador;
import com.br.timn.Gosto.service.interfaces.crud.AvaliadorServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemUpdateAvaliador;
import com.br.timn.Gosto.util.ConvertorChar256;
import com.br.timn.Gosto.util.GeradorImagemPadraoAvaliador;
import com.br.timn.Gosto.validation.ValidacaoEmailUnico;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;

@Service
public class ImpAvaliadorServiceCrud implements AvaliadorServiceCrud {

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private ImagemUpdateAvaliador imagemUpdateAvaliador;

  @Override
  public Avaliador createAvaliador(Avaliador avaliador) throws  IOException, Exception {
    ValidacaoEmailUnico.validarEmailUnicoAvaliador(avaliador.getEmailAvaliador(), avaliadorRepository);
    ValidacaoEmailUnico.validarEmailUnicoEstabelecimento(avaliador.getEmailAvaliador(), estabelecimentoRepository);
    if (avaliador.getNomeAvaliador() == null) {
      throw new AvaliadorNotFoundException("ERROR: Nome do avaliador não pode ser nulo.");
    }
    if (avaliador.getImagemPerfil() == null) {
      Imagem imagemPerfilPadrao = GeradorImagemPadraoAvaliador.carregarImagemPerfilPadrao(avaliador);
      imagemPerfilPadrao.setAvaliadorImagem(avaliador);
      avaliador.setImagemPerfil(imagemPerfilPadrao);
    }
    if (avaliador.getImagemBanner() == null) {
      Imagem imagemBannerPadrao = GeradorImagemPadraoAvaliador.carregarImagemBannerPadrao(avaliador);
      imagemBannerPadrao.setAvaliadorImagem(avaliador);
      avaliador.setImagemBanner(imagemBannerPadrao);
    }
    Avaliador createdAvaliador = avaliadorRepository.save(avaliador);
    return createdAvaliador;
  }

  @Override
  public Avaliador getAvaliadorById(Long id){
    return avaliadorRepository.findById(id)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador com ID " + id + " não encontrado."));
  }

  @Override
  public List<Avaliador> getAllAvaliadores() {
    return avaliadorRepository.findAll();
  }

  @Override
  public void deleteAvaliador(Long id) {
    Avaliador avaliador = avaliadorRepository.findById(id)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador com ID " + id + " não encontrado."));
    avaliadorRepository.deleteById(avaliador.getIdAvaliador());
  }

  @Override
  public Avaliador updateAvaliador(UpdateAvaliador update) throws ValidacaoException, IOException {
    Avaliador avaliador = avaliadorRepository.findById(update.getId())
        .orElseThrow(
            () -> new AvaliadorNotFoundException("ERROR: Avaliador com ID " + update.getId() + " não encontrado."));
    avaliador.setNomeAvaliador(update.getNome());
    avaliador.setTelefoneAvaliador(update.getTelefone());
    avaliador.setDataNascimentoAvaliador(update.getDataNascimento());
    String imagemCharPerfil = update.getCharImagemPerfil();
    String imagemCharBanner = update.getCharImagemBanner();
    byte[] imagemBytesPerfil = ConvertorChar256.convertChar256ToBytes(imagemCharPerfil);
    byte[] imagemBytesBanner = ConvertorChar256.convertChar256ToBytes(imagemCharBanner);
      avaliador.setImagemPerfil(imagemUpdateAvaliador.updateImagemPerfil(avaliador, imagemBytesPerfil, "perfil"));
      avaliador.setImagemBanner(imagemUpdateAvaliador.updateImagemBanner(avaliador, imagemBytesBanner, "banner"));
    return avaliadorRepository.save(avaliador);
  }

  @Override
  public Avaliador getAvaliadorByEmail(String email)  {
    return avaliadorRepository.findByEmailAvaliador(email)
        .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador com email " + email + " não encontrado."));
  }
}
