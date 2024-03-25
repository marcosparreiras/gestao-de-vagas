package com.marcosparreiras.gestao_vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Vaga para desenvolvedor júnior")
  private String description;

  @Schema(example = "Gympass e plano de saúde")
  private String benefits;

  @Schema(example = "Júnior")
  private String level;

  @ManyToOne
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private CompanyEntity companyEntity;

  @Column(name = "company_id")
  private UUID companyId;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
