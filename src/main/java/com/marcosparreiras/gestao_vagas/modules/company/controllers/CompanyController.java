package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.GetCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @Autowired
  private GetCompanyService getCompanyService;

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

  @GetMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  @Tag(name = "Company", description = "Recupera dados da empresa")
  @Operation(
    summary = "Recupera dados da empresa",
    description = "Recupera dados da empresa"
  )
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    try {
      String companyId = request.getAttribute("company_id").toString();
      CompanyEntity company =
        this.getCompanyService.execute(UUID.fromString(companyId));
      return ResponseEntity.status(HttpStatus.OK).body(company);
    } catch (Exception error) {
      return ResponseEntity.badRequest().body(error.getMessage());
    }
  }
}
