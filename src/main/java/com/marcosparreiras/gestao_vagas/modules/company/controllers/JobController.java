package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CreateJobRequestDTO createJobRequestDTO,
    HttpServletRequest request
  ) {
    var companyId = request.getAttribute("company_id");
    createJobRequestDTO.setCompanyId(companyId.toString());
    var result = this.createJobUseCase.execute(createJobRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
