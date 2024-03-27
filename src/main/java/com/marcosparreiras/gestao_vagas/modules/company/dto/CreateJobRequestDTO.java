package com.marcosparreiras.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequestDTO {

  @NotBlank(message = "Description is a required field")
  @Schema(
    example = "Vaga para pessoa desenvolvedora júnior",
    requiredMode = RequiredMode.REQUIRED
  )
  private String description;

  @NotBlank(message = "benefits is a required field")
  @Schema(
    example = "Gympass e plano de saúde",
    requiredMode = RequiredMode.REQUIRED
  )
  private String benefits;

  @NotBlank(message = "level is a required field")
  @Schema(example = "Júnior", requiredMode = RequiredMode.REQUIRED)
  private String level;

  @Schema(requiredMode = RequiredMode.NOT_REQUIRED)
  private String companyId;
}
