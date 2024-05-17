package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.CompanyNotFoundException;
import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(UUID companyId) {
    CompanyEntity company =
      this.companyRepository.findById(companyId)
        .orElseThrow(CompanyNotFoundException::new);
    return company;
  }
}
