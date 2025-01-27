package com.br.timn.Gosto.service.interfaces.security;


import com.br.timn.Gosto.model.Avaliador;

public interface AvaliadorLoginService {
    Avaliador login(String emailAvaliador, String senhaAvaliador);
}
