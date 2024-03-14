package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.UserFoundException;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidate) {
    this.candidateRepository.findByUserNameOrEmail(
        candidate.getUserName(),
        candidate.getEmail()
      )
      .ifPresent(user -> {
        throw new UserFoundException();
      });

    var password = this.passwordEncoder.encode(candidate.getPassword());
    candidate.setPassword(password);

    return this.candidateRepository.save(candidate);
  }
}
