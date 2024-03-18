package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.UserNotFoundExcepiton;
import com.marcosparreiras.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @SuppressWarnings("null")
  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate =
      this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundExcepiton();
        });

    var candidateDTO = ProfileCandidateResponseDTO
      .builder()
      .id(candidate.getId())
      .name(candidate.getName())
      .userName(candidate.getUserName())
      .email(candidate.getEmail())
      .description(candidate.getDescription())
      .build();

    return candidateDTO;
  }
}
