package com.marcosparreiras.gestao_vagas.modules.candidate.controllers;

import com.marcosparreiras.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidate {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/candidate")
  public ResponseEntity<Object> create(
    @Valid @RequestBody AuthCandidateDTO authCandidateDTO
  ) {
    try {
      var token = this.authCandidateUseCase.execute(authCandidateDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(token);
    } catch (Exception error) {
      return ResponseEntity.badRequest().body(error.getMessage());
    }
  }
}
