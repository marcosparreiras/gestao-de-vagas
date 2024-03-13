package com.marcosparreiras.gestao_vagas.modules.company.repositories;

import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUserNameOrEmail(String userName, String email);
}
