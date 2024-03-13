package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  @Autowired
  JobRepository jobRepository;

  public JobEntity execute(JobEntity job) throws Exception {
    if (job == null) {
      throw new Exception("Job can't be null");
    }
    return this.jobRepository.save(job);
  }
}
