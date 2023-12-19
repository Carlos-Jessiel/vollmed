package br.com.med.voll.api.infra.security;

import lombok.Builder;

@Builder
public record DadosTokenJwtDTO(
        String token
) {
}
