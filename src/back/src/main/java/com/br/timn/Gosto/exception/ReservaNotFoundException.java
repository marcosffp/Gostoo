package com.br.timn.Gosto.exception;

public class ReservaNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ReservaNotFoundException(String message) {
    super(message);
  }
}
