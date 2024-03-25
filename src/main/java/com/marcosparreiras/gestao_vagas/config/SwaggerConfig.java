package com.marcosparreiras.gestao_vagas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  OpenAPI openApi() {
    return new OpenAPI()
      .info(
        new Info()
          .title("Gestão de vagas")
          .description("Api responsável pela gestão de vagas")
          .version("1.0.0")
      )
      .schemaRequirement("jwt_auth", this.createSecuritySchema());
  }

  private SecurityScheme createSecuritySchema() {
    return new SecurityScheme()
      .name("jwt_auth")
      .type(SecurityScheme.Type.HTTP)
      .scheme("bearer")
      .bearerFormat("JWT");
  }
}
