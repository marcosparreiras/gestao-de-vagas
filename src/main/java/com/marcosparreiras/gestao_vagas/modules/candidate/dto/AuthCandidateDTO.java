package com.marcosparreiras.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthCandidateDTO {

  @NotBlank(message = "userName is a required field")
  private String userName;

  @NotBlank(message = "password is a required field")
  private String password;
}
