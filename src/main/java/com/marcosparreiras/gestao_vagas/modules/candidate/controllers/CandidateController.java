package com.marcosparreiras.gestao_vagas.modules.candidate.controllers;

import com.marcosparreiras.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import com.marcosparreiras.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @PostMapping("/")
  @Operation(
    summary = "Castro de candidatos",
    description = "Essa função é responsável cadastrar um novo candidato"
  )
  @ApiResponses(
    {
      @ApiResponse(
        responseCode = "201",
        content = {
          @Content(schema = @Schema(implementation = CandidateEntity.class)),
        }
      ),
      @ApiResponse(responseCode = "400", description = "User already exists"),
    }
  )
  public ResponseEntity<Object> create(
    @Valid @RequestBody CandidateEntity candidate
  ) {
    try {
      var result = this.createCandidateUseCase.execute(candidate);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception error) {
      return ResponseEntity.badRequest().body(error.getMessage());
    }
  }

  @GetMapping("/profile")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Perfil do candidato",
    description = "Essa função é responsável por retornar as informações do perfil do candidato"
  )
  @ApiResponses(
    {
      @ApiResponse(
        responseCode = "200",
        content = {
          @Content(
            schema = @Schema(implementation = ProfileCandidateResponseDTO.class)
          ),
        }
      ),
      @ApiResponse(responseCode = "400", description = "User not found"),
    }
  )
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> index(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var proflie =
        this.profileCandidateUseCase.execute(
            UUID.fromString(idCandidate.toString())
          );
      return ResponseEntity.ok().body(proflie);
    } catch (Exception error) {
      return ResponseEntity.badRequest().body(error.getMessage());
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Listagem de vagas disponíveis para o candidato",
    description = "Essa função é responsável por listar todas as vagas deisponíveis baseadas no filtro"
  )
  @ApiResponses(
    {
      @ApiResponse(
        responseCode = "200",
        content = {
          @Content(
            array = @ArraySchema(
              schema = @Schema(implementation = JobEntity.class)
            )
          ),
        }
      ),
    }
  )
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> jobsIndex(@RequestParam String filter) {
    var jobs = this.listAllJobsByFilterUseCase.execute(filter);
    return ResponseEntity.ok().body(jobs);
  }
}
