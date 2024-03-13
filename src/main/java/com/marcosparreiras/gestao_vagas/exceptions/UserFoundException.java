package com.marcosparreiras.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {

  public UserFoundException() {
    super("User already exists");
  }
}
