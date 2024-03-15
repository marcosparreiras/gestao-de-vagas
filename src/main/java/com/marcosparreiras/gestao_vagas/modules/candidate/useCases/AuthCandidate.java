package com.marcosparreiras.gestao_vagas.modules.candidate.useCases;

import com.marcosparreiras.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.marcosparreiras.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.marcosparreiras.gestao_vagas.providers.JWTProvider;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCandidate {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JWTProvider jwtProvider;

  public String execute(AuthCandidateDTO authCandidateDTO)
    throws AuthenticationException {
    var candidate =
      this.candidateRepository.findByUserName(authCandidateDTO.getUserName());
    if (candidate == null) {
      throw new AuthenticationException("Invalid credentials");
    }

    var passwordIsValid =
      this.passwordEncoder.matches(
          authCandidateDTO.getPassword(),
          candidate.getPassword()
        );
    if (!passwordIsValid) {
      throw new AuthenticationException("Invalid credentials");
    }

    var token = jwtProvider.generateToken(candidate.getId().toString());

    return token;
  }
}
