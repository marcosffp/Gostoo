package com.br.timn.Gosto.service.implementacao.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.security.JwtTokenUtil;
import com.br.timn.Gosto.service.interfaces.security.GerarTokenParaEstabelecimento;

@Service
public class ImpGerarTokenParaEstabelecimento implements GerarTokenParaEstabelecimento {
    @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public String gerarTokenParaEstabelecimento(Estabelecimento estabelecimento, Long id) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(estabelecimento.getEmailEstabelecimento());
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", id);
    claims.put("type", 1);

    return jwtTokenUtil.generateToken(claims, userDetails);
  }
  
}
