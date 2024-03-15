package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobDTO;
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
  public JobEntity execute(CreateJobDTO createJobDTO) {
    JobEntity job = JobEntity
      .builder()
      .description(createJobDTO.getDescription())
      .benefits(createJobDTO.getBenefits())
      .level(createJobDTO.getLevel())
      .companyId(UUID.fromString(createJobDTO.getCompanyId()))
      .build();

    return this.jobRepository.save(job);
  }
}
