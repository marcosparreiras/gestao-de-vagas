package com.marcosparreiras.gestao_vagas.modules.candidate.repositories;

import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository
  extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByUserNameOrEmail(
    String userName,
    String email
  );
}
