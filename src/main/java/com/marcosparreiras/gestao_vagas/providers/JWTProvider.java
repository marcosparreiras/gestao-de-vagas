package com.marcosparreiras.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

  @Value("${security.token.secret}")
  private String secretKey;

  public String validateToken(String token) throws JWTVerificationException {
    token = token.replace("Bearer ", "");
    var subject = JWT
      .require(this.getAlgorithm())
      .build()
      .verify(token)
      .getSubject();
    return subject;
  }

  public String generateToken(String id) {
    var token = JWT
      .create()
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withSubject(id)
      .sign(this.getAlgorithm());
    return token;
  }

  private Algorithm getAlgorithm() {
    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    return algorithm;
  }
}