package com.br.timn.Gosto.util;

import java.util.Base64;

public class ConvertorChar256 {

  public static byte[] convertChar256ToBytes(String imagemChar) {
    if (imagemChar == null || imagemChar.isEmpty()) {
      throw new IllegalArgumentException("A imagem fornecida está vazia.");
    }
    try {
      return Base64.getDecoder().decode(imagemChar);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Formato de imagem inválido.");
    }
  }

  public static String convertBytesToChar256(byte[] imagemBytes) {
    return java.util.Base64.getEncoder().encodeToString(imagemBytes);
  }

}
