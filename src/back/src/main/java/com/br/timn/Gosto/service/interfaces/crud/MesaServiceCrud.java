package com.br.timn.Gosto.service.interfaces.crud;

import java.util.List;
import java.util.Map;
import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.get.GetMesa;
import com.br.timn.Gosto.model.update.UpdateMesa;

public interface MesaServiceCrud {
  public Mesa createMesa(Map<String, Object> dados) throws Exception;

  public GetMesa getMesaById(Long id);

  public List<GetMesa> getAllMesas();

  public void deleteMesa(Long id);

  public Mesa updateMesa(UpdateMesa update);
}
