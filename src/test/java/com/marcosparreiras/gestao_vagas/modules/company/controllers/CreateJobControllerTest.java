package com.marcosparreiras.gestao_vagas.modules.company.controllers;

import com.marcosparreiras.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import com.marcosparreiras.gestao_vagas.modules.company.entities.CompanyEntity;
import com.marcosparreiras.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.marcosparreiras.gestao_vagas.providers.JWTProvider;
import com.marcosparreiras.gestao_vagas.utils.TestUtils;
import java.util.Arrays;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private JWTProvider jwtProvider;

  @Before
  @SuppressWarnings("null")
  public void setup() {
    mvc =
      MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @Test
  @DisplayName("Should be able to create a job")
  @SuppressWarnings("null")
  public void create_job() throws Exception {
    var company =
      this.companyRepository.save(
          CompanyEntity
            .builder()
            .name("mu company")
            .userName("my-company")
            .email("mycompany@example.com")
            .password("123456")
            .build()
        );

    var token =
      this.jwtProvider.generateToken(
          company.getId().toString(),
          Arrays.asList("COMPANY")
        );

    var requestData = TestUtils.ObjectToJSON(
      CreateJobRequestDTO
        .builder()
        .description("Some description")
        .benefits("Some benefits")
        .level("Some level")
        .build()
    );

    mvc
      .perform(
        MockMvcRequestBuilders
          .post("/company/job/")
          .header("Authorization", "Bearer " + token)
          .contentType(MediaType.APPLICATION_JSON)
          .content(requestData)
      )
      .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("Should not be able to create a job with an invalid company")
  @SuppressWarnings("null")
  public void create_job_error() throws Exception {
    var requestData = TestUtils.ObjectToJSON(
      CreateJobRequestDTO
        .builder()
        .description("Some description")
        .benefits("Some benefits")
        .level("Some level")
        .build()
    );

    var token =
      this.jwtProvider.generateToken(
          UUID.randomUUID().toString(),
          Arrays.asList("COMPANY")
        );

    mvc
      .perform(
        MockMvcRequestBuilders
          .post("/company/job/")
          .header("Authorization", "Bearer " + token)
          .contentType(MediaType.APPLICATION_JSON)
          .content(requestData)
      )
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
