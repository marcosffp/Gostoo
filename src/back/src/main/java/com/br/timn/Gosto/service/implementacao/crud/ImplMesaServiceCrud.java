package com.br.timn.Gosto.service.implementacao.crud;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.exception.MesaNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.get.GetMesa;
import com.br.timn.Gosto.model.update.UpdateMesa;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;
import com.br.timn.Gosto.repository.MesaRepository;
import com.br.timn.Gosto.service.interfaces.crud.MesaServiceCrud;

@Service
public class ImplMesaServiceCrud implements MesaServiceCrud {

  @Autowired
  private EstabelecimentoRepository estabelecimentoRepository;

  @Autowired
  private MesaRepository mesaRepository;

  @Override
  public Mesa createMesa(Map<String, Object> dados) throws MesaNotFoundException, Exception,EstabelecimentoNotFoundException {
      Long idEstabelecimento = Long.valueOf(dados.get("idEstabelecimento").toString());
      String descricaoMesa = dados.get("descricaoMesa").toString();
      int capacidadeMesa = Integer.parseInt(dados.get("capacidadeMesa").toString());
      String nomeMesa = dados.get("nomeMesa").toString();
      Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento)
          .orElseThrow(() -> new EstabelecimentoNotFoundException("ERROR: Estabelecimento não encontrado!"));
      
      Mesa novaMesa = new Mesa();
      novaMesa.setNomeMesa(nomeMesa);
      novaMesa.setEstabelecimentoMesa(estabelecimento);
      novaMesa.setDescricaoMesa(descricaoMesa);
      novaMesa.setCapacidadeMesa(capacidadeMesa);

      estabelecimento.getMesasEstabelecimento().add(novaMesa);
      estabelecimentoRepository.save(estabelecimento);

      return novaMesa;
 
  }

  @Override
  public GetMesa getMesaById(Long id) throws MesaNotFoundException {
    Mesa mesa = mesaRepository.findById(id)
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada!"));
    return new GetMesa(mesa);
  }

  @Override
  public void deleteMesa(Long id) throws MesaNotFoundException {
    Mesa mesa = mesaRepository.findById(id)
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada!"));
    mesaRepository.delete(mesa);
  }

  @Override
  public List<GetMesa> getAllMesas() throws MesaNotFoundException {
    return mesaRepository.findAll().stream()
        .map(mesa -> new GetMesa(mesa))
        .collect(Collectors.toList());
  }

  @Override
  public Mesa updateMesa(UpdateMesa update) throws MesaNotFoundException {
    Mesa mesa = mesaRepository.findById(update.getId())
        .orElseThrow(() -> new MesaNotFoundException("ERROR: Mesa não encontrada!"));
    mesa.setNomeMesa(update.getNome());
    mesa.setDescricaoMesa(update.getDescricao());
    mesa.setCapacidadeMesa(update.getCapacidade());
    return mesaRepository.save(mesa);
  }
}
