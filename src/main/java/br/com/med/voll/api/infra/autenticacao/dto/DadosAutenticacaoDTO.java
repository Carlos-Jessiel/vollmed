package br.com.med.voll.api.infra.autenticacao.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacaoDTO(

        @NotNull
        String login,
        @NotNull
        String senha
) {
}
