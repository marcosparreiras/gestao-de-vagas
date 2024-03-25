package com.marcosparreiras.gestao_vagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "John Doe")
  private String name;

  @Schema(example = "Desenvolvedor Java júnior")
  private String description;

  private String curriculum;

  @Pattern(
    regexp = "^\\S+$",
    message = "userName field should not have white spaces"
  )
  @Schema(example = "john-doe")
  private String userName;

  @Email(message = "Email field should contain a valid email")
  @Schema(example = "johndoe@example.com")
  private String email;

  @Length(
    min = 6,
    max = 100,
    message = "Password field should have at least 6 characters"
  )
  @Schema(example = "user@1234")
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
