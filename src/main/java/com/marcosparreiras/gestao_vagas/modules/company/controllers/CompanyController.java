package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CompanyEntity company
  ) {
    try {
      var result = this.createCompanyUseCase.execute(company);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception error) {
      return ResponseEntity.badRequest().body(error.getMessage());
    }
  }
}
