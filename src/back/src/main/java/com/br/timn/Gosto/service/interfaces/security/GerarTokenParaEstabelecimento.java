package com.br.timn.Gosto.service.interfaces.security;

import com.br.timn.Gosto.model.Estabelecimento;

public interface GerarTokenParaEstabelecimento {
    String gerarTokenParaEstabelecimento(Estabelecimento estabelecimento, Long id);
}
