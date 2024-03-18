package com.marcosparreiras.gestao_vagas.exceptions;

public class UserNotFoundExcepiton extends RuntimeException {

  public UserNotFoundExcepiton() {
    super("User not found");
  }
}
