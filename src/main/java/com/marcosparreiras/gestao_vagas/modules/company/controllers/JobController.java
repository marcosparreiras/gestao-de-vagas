package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import com.marcosparreiras.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  @Tag(name = "Vagas", description = "Informações das vags")
  @Operation(
    summary = "Cadastro de vaga",
    description = "Essa função é responsável por cadastrar as vagas dentro da empresa"
  )
  @ApiResponses(
    {
      @ApiResponse(
        responseCode = "201",
        content = {
          @Content(schema = @Schema(implementation = JobEntity.class)),
        }
      ),
    }
  )
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CreateJobRequestDTO createJobRequestDTO,
    HttpServletRequest request
  ) {
    try {
      var companyId = request.getAttribute("company_id");
      createJobRequestDTO.setCompanyId(companyId.toString());
      var result = this.createJobUseCase.execute(createJobRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
