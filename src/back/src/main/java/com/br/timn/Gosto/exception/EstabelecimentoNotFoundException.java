package com.br.timn.Gosto.exception;

public class EstabelecimentoNotFoundException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public EstabelecimentoNotFoundException(String msg) {
        super(msg);
    }
}

