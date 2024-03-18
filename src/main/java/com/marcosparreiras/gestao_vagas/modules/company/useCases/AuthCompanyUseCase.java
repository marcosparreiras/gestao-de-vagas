package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.marcosparreiras.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.marcosparreiras.gestao_vagas.providers.JWTProvider;
import java.util.Arrays;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

  @Autowired
  private JWTProvider JWTProvider;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO)
    throws AuthenticationException {
    var comapny =
      this.companyRepository.findByUserName(authCompanyDTO.getUserName());
    if (comapny == null) {
      throw new AuthenticationException("Invalid credentials");
    }

    var passwordIsValid =
      this.passwordEncoder.matches(
          authCompanyDTO.getPassword(),
          comapny.getPassword()
        );
    if (!passwordIsValid) {
      throw new AuthenticationException("Invalid credentials");
    }

    var token =
      this.JWTProvider.generateToken(
          comapny.getId().toString(),
          Arrays.asList("COMPANY")
        );

    return token;
  }
}
