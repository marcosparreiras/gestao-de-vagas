package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.dto.AuthCompanyRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> create(
    @RequestBody AuthCompanyRequestDTO authCompanyRequestDTO
  ) {
    try {
      var result = authCompanyUseCase.execute(authCompanyRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception error) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(error.getMessage());
    }
  }
}
