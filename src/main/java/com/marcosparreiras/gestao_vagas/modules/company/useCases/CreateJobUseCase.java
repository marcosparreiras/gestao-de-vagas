package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.CompanyNotFoundException;
import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @SuppressWarnings("null")
  public JobEntity execute(CreateJobRequestDTO createJobRequestDTO) {
    this.companyRepository.findById(
        UUID.fromString(createJobRequestDTO.getCompanyId())
      )
      .orElseThrow(() -> {
        throw new CompanyNotFoundException();
      });

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
