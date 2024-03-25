package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    var jobs = this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    return jobs;
  }
}
