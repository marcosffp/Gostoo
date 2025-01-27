package com.br.timn.Gosto.service.interfaces.security;

import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.repository.AvaliadorRepository;
import com.br.timn.Gosto.repository.EstabelecimentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AvaliadorRepository avaliadorRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Avaliador avaliador = avaliadorRepository.findByEmailAvaliador(email).orElse(null);
        if (avaliador != null) {
            return new User(avaliador.getEmailAvaliador(), avaliador.getSenhaAvaliador(), getAvaliadorAuthorities());
        }
        Estabelecimento estabelecimento = estabelecimentoRepository.findByEmailEstabelecimento(email).orElse(null);
        if (estabelecimento != null) {
            return new User(estabelecimento.getEmailEstabelecimento(), estabelecimento.getSenhaEstabelecimento(),
                    getEstabelecimentoAuthorities());
        }
        throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
    }

    private Collection<? extends GrantedAuthority> getAvaliadorAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_AVALIADOR");
    }

    private Collection<? extends GrantedAuthority> getEstabelecimentoAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_ESTABELECIMENTO");
    }
}
