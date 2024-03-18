package com.marcosparreiras.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyRequestDTO {

  @NotBlank(message = "userName is a required field")
  private String userName;

  @NotBlank(message = "password is a required field")
  private String password;
}
