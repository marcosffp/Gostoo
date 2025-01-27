package com.br.timn.Gosto.service.interfaces.crud;

import java.io.IOException;
import java.util.List;

import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.update.UpdateAvaliador;

public interface AvaliadorServiceCrud {
    public Avaliador createAvaliador(Avaliador avaliador) throws Exception;

    public Avaliador getAvaliadorByEmail(String email);

    public Avaliador getAvaliadorById(Long id);

    public List<Avaliador> getAllAvaliadores();

    public void deleteAvaliador(Long id);

    public Avaliador updateAvaliador(UpdateAvaliador update) throws Exception, IOException;
}
