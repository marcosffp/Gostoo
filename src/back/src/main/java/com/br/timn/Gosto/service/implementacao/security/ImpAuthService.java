package com.br.timn.Gosto.service.implementacao.security;

import com.br.timn.Gosto.security.JwtResponse;
import com.br.timn.Gosto.security.JwtTokenUtil;
import com.br.timn.Gosto.security.LoginRequest;
import com.br.timn.Gosto.service.interfaces.security.AuthService;
import com.br.timn.Gosto.service.interfaces.security.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ImpAuthService implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) throws Exception {
        validarLoginRequest(loginRequest);
        autenticarUsuario(loginRequest);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Map<String, Object> claims = gerarClaims(loginRequest.getEmail(), userDetails);
        String token = jwtTokenUtil.generateToken(claims, userDetails);
        return new JwtResponse(token);
    }

    @Override
    public Map<String, Object> gerarClaims(String email, UserDetails userDetails) {
        Long id = userService.getUserIdByEmail(email);
        Integer type = userService.getUserTypeByEmail(email);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("type", type);
        return claims;
    }

    @Override
    public void autenticarUsuario(LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getSenha()));
        } catch (DisabledException e) {
            throw new Exception("Usuário desativado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais inválidas", e);
        }
    }

    @Override
    public void validarLoginRequest(LoginRequest loginRequest) {
        Objects.requireNonNull(loginRequest.getEmail(), "O email não pode ser nulo");
        Objects.requireNonNull(loginRequest.getSenha(), "A senha não pode ser nula");
    }

}
