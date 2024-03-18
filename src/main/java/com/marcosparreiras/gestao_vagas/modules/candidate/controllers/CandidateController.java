package com.marcosparreiras.gestao_vagas.modules.candidate.controllers;

import com.marcosparreiras.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.marcosparreiras.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping("/")
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
}
