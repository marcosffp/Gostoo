package com.br.timn.Gosto.service.implementacao.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.exception.AvaliacaoNotFoundException;
import com.br.timn.Gosto.exception.AvaliadorNotFoundException;
import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.get.GetAvaliacao;
import com.br.timn.Gosto.model.update.UpdateAvaliacao;
import com.br.timn.Gosto.repository.AvaliacaoRepository;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.service.interfaces.crud.AvaliacaoServiceCrud;
import com.br.timn.Gosto.util.CalcularNota;

@Service
public class ImpAvaliacaoServiceCrud implements AvaliacaoServiceCrud {

  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private AvaliadorRepository avaliadorRepository;

  @Override
  public Avaliacao createAvaliacao(Map<String, Object> dados) throws Exception {
      Long idAvaliador = Long.valueOf(dados.get("idAvaliador").toString());
      Long idEstabelecimento = Long.valueOf(dados.get("idEstabelecimento").toString());
      String descricaoAvaliacao = dados.get("descricaoAvaliacao").toString();
      double notaAvaliacao = Double.parseDouble(dados.get("notaAvaliacao").toString());
      LocalDate dataAvaliacao = LocalDate.parse(dados.get("dataAvaliacao").toString());
      Avaliador avaliador = avaliadorRepository.findById(idAvaliador)
          .orElseThrow(() -> new AvaliadorNotFoundException("ERROR: Avaliador não encontrado com id: " + idAvaliador));
      Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento)
          .orElseThrow(() -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado com id: " + idEstabelecimento));
      Avaliacao novaAvaliacao = new Avaliacao();
      novaAvaliacao.setAvaliador(avaliador);
      novaAvaliacao.setEstabelecimento(estabelecimento);
      novaAvaliacao.setDescricaoAvaliacao(descricaoAvaliacao);
      novaAvaliacao.setNotaAvaliacao(notaAvaliacao);
      novaAvaliacao.setDataAvaliacao(dataAvaliacao);
      Avaliacao avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);
      double novaNotaEstabelecimento = CalcularNota.calcularMediaNotas(estabelecimento);
      estabelecimento.setNotaEstabelecimento(novaNotaEstabelecimento);
      avaliador.getAvaliacoesAvaliador().add(avaliacaoSalva);
      estabelecimento.getAvaliacoesEstabelecimento().add(avaliacaoSalva);
      avaliadorRepository.save(avaliador);
      estabelecimentoRepository.save(estabelecimento);
      return avaliacaoSalva;
  }

  @Override
  public GetAvaliacao getAvaliacaoById(Long id){
    Avaliacao avaliacao = avaliacaoRepository.findById(id)
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliacao não encontrada com id: " + id));
    return new GetAvaliacao(avaliacao);
  }

  @Override
  public List<GetAvaliacao> getAllAvaliacoes() {
    return avaliacaoRepository.findAll().stream()
        .map(avaliacao -> new GetAvaliacao(avaliacao))
        .collect(Collectors.toList());
  }

  @Override
  public void deleteAvaliacao(Long id) {
    Avaliacao avaliacao = avaliacaoRepository.findById(id)
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliacao não encontrada com id: " + id));
    avaliacaoRepository.delete(avaliacao);
  }

  @Override
  public Avaliacao updateAvaliacao(UpdateAvaliacao update) throws Exception {
    Avaliacao avaliacao = avaliacaoRepository.findById(update.getId())
        .orElseThrow(() -> new AvaliacaoNotFoundException("ERROR: Avaliacao não encontrada com id: " + update.getId()));
    avaliacao.setDataAvaliacao(update.getData());
    avaliacao.setDescricaoAvaliacao(update.getDescricao());
    avaliacao.setNotaAvaliacao(update.getNota());
    return avaliacaoRepository.save(avaliacao);
  }
}
