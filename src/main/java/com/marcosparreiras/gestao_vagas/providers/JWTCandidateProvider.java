package com.marcosparreiras.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {

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

  private Algorithm getAlgorithm() {
    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    return algorithm;
  }
}
