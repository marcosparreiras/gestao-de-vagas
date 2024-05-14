package com.marcosparreiras.gestao_vagas.modules.company.dto;

import java.util.List;

public record AuthCompanyResponseDTO(String token, List<String> roles) {}
