package com.br.timn.Gosto.service.implementacao.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.security.JwtTokenUtil;
import com.br.timn.Gosto.service.interfaces.security.GerarTokenParaAvaliador;

@Service
public class ImpGerarTokenParaAvaliador implements GerarTokenParaAvaliador {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public String gerarTokenParaAvaliador(Avaliador avaliadorDto, Long id) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(avaliadorDto.getEmailAvaliador());
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", id);
    claims.put("type", 2);

    return jwtTokenUtil.generateToken(claims, userDetails);
  }

}