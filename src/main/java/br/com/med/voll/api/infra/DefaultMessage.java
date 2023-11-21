package br.com.med.voll.api.infra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DefaultMessage implements MessageProperties {

    NOT_FOUND("naoEncontrado.message"),
    MUST_BE_FILLED("especialidade.obrigatoria.message");

    private final String messageProperty;
}
