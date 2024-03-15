package com.marcosparreiras.gestao_vagas.modules.candidate.controllers;

import com.marcosparreiras.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
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
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/candidate")
  public ResponseEntity<Object> auth(
    @Valid @RequestBody AuthCandidateRequestDTO authCandidateRequestDTO
  ) {
    try {
      var response = this.authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception error) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(error.getMessage());
    }
  }
}
