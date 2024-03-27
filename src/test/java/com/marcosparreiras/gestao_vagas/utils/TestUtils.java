package com.marcosparreiras.gestao_vagas.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

  public static String ObjectToJSON(Object obj) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
