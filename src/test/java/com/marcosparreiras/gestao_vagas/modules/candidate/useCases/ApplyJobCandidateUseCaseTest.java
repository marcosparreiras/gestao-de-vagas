package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.marcosparreiras.gestao_vagas.exceptions.JobNotFoundException;
import com.marcosparreiras.gestao_vagas.exceptions.UserNotFoundExcepiton;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase sut;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @SuppressWarnings("null")
  @Test
  @DisplayName("Should be able to apply a candidate to a job")
  public void applyJob() {
    var candidate = new CandidateEntity();
    candidate.setId(UUID.randomUUID());

    var job = new JobEntity();
    job.setId(UUID.randomUUID());

    var applyJob = ApplyJobEntity
      .builder()
      .candidateId(candidate.getId())
      .jobId(job.getId())
      .build();

    when(this.jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
    when(this.candidateRepository.findById(candidate.getId()))
      .thenReturn(Optional.of(candidate));
    when(this.applyJobRepository.save(applyJob)).thenReturn(applyJob);

    var result =
      this.sut.execute(job.getId().toString(), candidate.getId().toString());

    assertThat(result).isInstanceOf(ApplyJobEntity.class);
    assertEquals(result.getCandidateId(), candidate.getId());
    assertEquals(result.getJobId(), job.getId());
  }

  @Test
  @DisplayName(
    "Should not be able to apply for a job with unexistent candidate id"
  )
  public void user_not_found() {
    try {
      this.sut.execute(
          UUID.randomUUID().toString(),
          UUID.randomUUID().toString()
        );
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundExcepiton.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply for an unexistent job")
  @SuppressWarnings("null")
  public void job_not_found() {
    var candidate = new CandidateEntity();
    candidate.setId(UUID.randomUUID());

    when(candidateRepository.findById(candidate.getId()))
      .thenReturn(Optional.of(candidate));
    try {
      this.sut.execute(
          UUID.randomUUID().toString(),
          candidate.getId().toString()
        );
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundException.class);
    }
  }
}
