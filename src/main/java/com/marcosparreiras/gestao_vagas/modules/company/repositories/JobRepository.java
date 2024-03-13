package com.marcosparreiras.gestao_vagas.modules.company.repositories;

import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {}
