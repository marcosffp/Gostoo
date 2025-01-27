package com.br.timn.Gosto.service.interfaces.security;

public interface UserService {
  Long getUserIdByEmail(String email);
  Integer getUserTypeByEmail(String email);
}


  