package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.JobNotFoundException;
import com.marcosparreiras.gestao_vagas.exceptions.UserNotFoundExcepiton;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  @SuppressWarnings("null")
  public ApplyJobEntity execute(String jobId, String candidateId)
    throws UserNotFoundExcepiton, JobNotFoundException {
    var candidate =
      this.candidateRepository.findById(UUID.fromString(candidateId))
        .orElseThrow(() -> {
          throw new UserNotFoundExcepiton();
        });

    var job =
      this.jobRepository.findById(UUID.fromString(jobId))
        .orElseThrow(() -> {
          throw new JobNotFoundException();
        });

    var applyJob = ApplyJobEntity
      .builder()
      .candidateId(candidate.getId())
      .jobId(job.getId())
      .build();

    applyJob = this.applyJobRepository.save(applyJob);
    return applyJob;
  }
}
