package com.marcosparreiras.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

  @Value("${security.token.secret}")
  private String secretKey;

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "");
    var algorithm = this.getAlgorithm();
    try {
      var tokenDecoded = JWT.require(algorithm).build().verify(token);
      return tokenDecoded;
    } catch (JWTVerificationException error) {
      error.printStackTrace();
      return null;
    }
  }

  public String generateToken(String id, List<String> roles) {
    var algorithm = this.getAlgorithm();
    var token = JWT
      .create()
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withClaim("roles", roles)
      .withSubject(id)
      .sign(algorithm);
    return token;
  }

  private Algorithm getAlgorithm() {
    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    return algorithm;
  }
}
