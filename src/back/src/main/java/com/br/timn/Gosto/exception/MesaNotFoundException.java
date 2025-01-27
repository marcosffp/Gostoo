package com.br.timn.Gosto.exception;

public class MesaNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MesaNotFoundException(String msg) {
    super(msg);
  }
}
