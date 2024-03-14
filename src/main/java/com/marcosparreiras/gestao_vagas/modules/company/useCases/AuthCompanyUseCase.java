package com.marcosparreiras.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.marcosparreiras.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

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

    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    var token = JWT
      .create()
      .withSubject(comapny.getId().toString())
      .sign(algorithm);
    return token;
  }
}
