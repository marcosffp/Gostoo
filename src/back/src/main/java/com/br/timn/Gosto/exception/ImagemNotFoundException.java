package com.br.timn.Gosto.exception;

public class ImagemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ImagemNotFoundException(String msg) {
        super(msg);
    }
}
