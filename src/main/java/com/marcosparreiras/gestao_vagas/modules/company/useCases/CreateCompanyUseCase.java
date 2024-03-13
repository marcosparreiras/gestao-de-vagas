package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.exceptions.UserFoundException;
import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity company) {
    this.companyRepository.findByUserNameOrEmail(
        company.getUserName(),
        company.getEmail()
      )
      .ifPresent(user -> {
        throw new UserFoundException();
      });

    return this.companyRepository.save(company);
  }
}
