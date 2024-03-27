package com.marcosparreiras.gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String webSite;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;

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
}
