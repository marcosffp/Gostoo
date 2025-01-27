package com.br.timn.Gosto.validation.validacaoEntidades;

import com.br.timn.Gosto.exception.ValidacaoException;

public abstract class Validacao {
  
  public static void validarNome(String nome) throws ValidacaoException {
    if (nome == null || nome.trim().isEmpty()) {
      throw new ValidacaoException("ERROR: Nome não pode ser vazio.");
    }
    if (!nome.trim().matches("[\\p{L}\\p{M}\\s]+")) {
      throw new ValidacaoException("ERROR: Nome deve conter apenas letras e espaços.");
    }
  }

  public static void validarEmail(String email) throws ValidacaoException {
    if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      throw new ValidacaoException("ERROR: formato de email inválido.");
    }
  }

  public static void validarTelefone(String telefone) throws ValidacaoException {
    telefone = telefone.replaceAll("\\s+", "").replaceAll("[^\\d]", ""); 

    if (!telefone.matches("\\d{10}|\\d{11}")) {
      throw new ValidacaoException("ERROR: Telefone deve ter 10 ou 11 dígitos.");
    }

    if (telefone.length() == 11 && telefone.charAt(2) != '9') {
      throw new ValidacaoException("ERROR: Telefone celular deve começar com 9 e ter 11 dígitos.");
    }
  }

  public static void validarSenha(String senha)  throws ValidacaoException{
    boolean minLength = senha.length() >= 6;
    boolean hasNumber = senha.matches(".*\\d.*");
    boolean hasSymbol = senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    boolean hasUppercase = senha.matches(".*[A-Z].*");
    boolean hasLowercase = senha.matches(".*[a-z].*");

    if (!minLength) {
      throw new ValidacaoException("ERROR: A senha deve ter pelo menos 6 caracteres.");
    }
    if (!hasNumber) {
      throw new ValidacaoException("ERROR: A senha deve conter pelo menos um número.");
    }
    if (!hasSymbol) {
      throw new ValidacaoException("ERROR: A senha deve conter pelo menos um símbolo.");
    }
    if (!hasUppercase) {
      throw new ValidacaoException("ERROR: A senha deve conter pelo menos uma letra maiúscula.");
    }
    if (!hasLowercase) {
      throw new ValidacaoException("ERROR: A senha deve conter pelo menos uma letra minúscula.");
    }
  }
}
