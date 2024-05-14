package com.marcosparreiras.gestao_vagas.modules.candidate.dto;

import java.util.List;

public record AuthCandidateResponseDTO(String token, List<String> roles) {}
