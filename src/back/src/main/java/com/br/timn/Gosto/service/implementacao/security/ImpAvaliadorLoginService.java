package com.br.timn.Gosto.service.implementacao.security;

import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.service.interfaces.security.AvaliadorLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ImpAvaliadorLoginService implements AvaliadorLoginService {

    @Autowired
    private AvaliadorRepository avaliadorRepository;

    @Override
    public Avaliador login(String emailAvaliador, String senhaAvaliador) {
        Avaliador avaliador = avaliadorRepository.findByEmailAvaliador(emailAvaliador)
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha inválidos"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(senhaAvaliador, avaliador.getSenhaAvaliador())) {
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

        // Retorna o próprio objeto Avaliador
        return avaliador;
    }
}
