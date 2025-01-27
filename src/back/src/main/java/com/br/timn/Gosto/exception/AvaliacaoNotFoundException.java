package com.br.timn.Gosto.exception;

public class AvaliacaoNotFoundException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    
    public AvaliacaoNotFoundException(String msg) {
        super(msg);
    }
}
