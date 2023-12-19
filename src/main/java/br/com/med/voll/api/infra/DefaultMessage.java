package br.com.med.voll.api.infra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DefaultMessage implements MessageProperties {

    NOT_FOUND("naoEncontrado.message"),
    MUST_BE_FILLED("especialidade.obrigatoria.message"),
    CONFLICT_SAME_TIME("consulta.mesmoHorario"),
    CONFLICT_SAME_DATE("consulta.mesmoData"),
    MINIMUM_NOTICE("consulta.antecedenciaMinima"),
    OUT_OF_SERVICE("consulta.foraDeFuncionamento");

    private final String messageProperty;
}
