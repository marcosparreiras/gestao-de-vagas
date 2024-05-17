package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListJobsUserCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(UUID companyId) {
    List<JobEntity> jobs = this.jobRepository.findByCompanyId(companyId);
    return jobs;
  }
}
