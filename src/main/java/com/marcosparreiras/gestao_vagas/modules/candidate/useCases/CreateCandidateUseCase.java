package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.UserFoundException;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

  @Autowired
  CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidate) {
    this.candidateRepository.findByUserNameOrEmail(
        candidate.getUserName(),
        candidate.getEmail()
      )
      .ifPresent(user -> {
        throw new UserFoundException();
      });

    return this.candidateRepository.save(candidate);
  }
}
