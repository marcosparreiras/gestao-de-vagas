package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> create(
    @Valid @RequestBody CreateJobDTO createJobDTO,
    HttpServletRequest request
  ) {
    var companyId = request.getAttribute("company_id");
    createJobDTO.setCompanyId(companyId.toString());
    var result = this.createJobUseCase.execute(createJobDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
