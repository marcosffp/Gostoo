package com.br.timn.Gosto.exception;

public class TagNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TagNotFoundException(String message) {
    super(message);
  }
  
}
