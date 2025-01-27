package com.br.timn.Gosto.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaCriptografada {
    public static String encode(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }
}
