package com.br.timn.Gosto.model.type;

public enum Tipo {
  PERFIL,
  BANNER,
  OUTRO;

  public static Tipo parseTipo(String tipo) {
    switch (tipo) {
      case "PERFIL":
        return Tipo.PERFIL;
      case "BANNER":
        return Tipo.BANNER;
      case "OUTRO":
        return Tipo.OUTRO;
      default:
        return null; 
    }
  }
}
