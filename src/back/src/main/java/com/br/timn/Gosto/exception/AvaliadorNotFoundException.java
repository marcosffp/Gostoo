package com.br.timn.Gosto.exception;

public class AvaliadorNotFoundException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public AvaliadorNotFoundException(String msg) {
        super(msg);
    }
}
