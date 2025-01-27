package com.br.timn.Gosto.service.interfaces.security;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.br.timn.Gosto.security.JwtResponse;
import com.br.timn.Gosto.security.LoginRequest;

public interface AuthService {
    JwtResponse authenticate(LoginRequest loginRequest) throws Exception;

    Map<String, Object> gerarClaims(String email, UserDetails userDetails);

    void autenticarUsuario(LoginRequest loginRequest) throws Exception;

    void validarLoginRequest(LoginRequest loginRequest);
}
