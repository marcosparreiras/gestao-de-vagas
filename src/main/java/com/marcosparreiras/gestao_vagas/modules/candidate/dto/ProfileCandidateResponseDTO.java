package com.marcosparreiras.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

  private UUID id;

  @Schema(example = "John Doe")
  private String name;

  @Schema(example = "Desenvolvedor Java júnior")
  private String description;

  @Schema(example = "john-doe")
  private String userName;

  @Schema(example = "johndoe@example.com")
  private String email;
}
