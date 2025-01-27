package com.br.timn.Gosto.model.type;

public enum Status {
  PENDENTE,
  REJEITADA,
  APROVADA;

  public static Status parseStatus(String status) {
    switch (status) {
      case "PENDENTE":
        return Status.PENDENTE;
      case "REJEITADA":
        return Status.REJEITADA;
      case "APROVADA":
        return Status.APROVADA;
      default:
        return null;
    }
  }
}
