package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  @Autowired
  JobRepository jobRepository;

  @SuppressWarnings("null")
  public JobEntity execute(CreateJobRequestDTO createJobRequestDTO) {
    JobEntity job = JobEntity
      .builder()
      .description(createJobRequestDTO.getDescription())
      .benefits(createJobRequestDTO.getBenefits())
      .level(createJobRequestDTO.getLevel())
      .companyId(UUID.fromString(createJobRequestDTO.getCompanyId()))
      .build();

    return this.jobRepository.save(job);
  }
}
