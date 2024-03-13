package com.marcosparreiras.gestao_vagas.modules.candidate.entities;

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

  private String name;
  private String description;
  private String curriculum;

  @Pattern(
    regexp = "^\\S+$",
    message = "userName field should not have white spaces"
  )
  private String userName;

  @Email(message = "Email field should contain a valid email")
  private String email;

  @Length(
    min = 6,
    max = 100,
    message = "Password field should have at least 6 characters"
  )
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
