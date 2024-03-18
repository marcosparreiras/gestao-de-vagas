package com.marcosparreiras.gestao_vagas.modules.candidate.dto;

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
  private String name;
  private String description;
  private String userName;
  private String email;
}
