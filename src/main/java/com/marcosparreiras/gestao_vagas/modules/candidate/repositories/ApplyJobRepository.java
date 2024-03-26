package com.marcosparreiras.gestao_vagas.modules.candidate.repositories;

import com.marcosparreiras.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository
  extends JpaRepository<ApplyJobEntity, UUID> {}
