package com.marcosparreiras.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobRequestDTO {

  @NotBlank(message = "Description is a required field")
  private String description;

  @NotBlank(message = "benefits is a required field")
  private String benefits;

  @NotBlank(message = "level is a required field")
  private String level;

  private String companyId;
}
