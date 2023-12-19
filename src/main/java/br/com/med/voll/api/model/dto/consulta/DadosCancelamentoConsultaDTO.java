package br.com.med.voll.api.model.dto.consulta;

import br.com.med.voll.api.service.validacoes.cancelamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDTO(

        @NotNull
        MotivoCancelamento motivoCancelamento

) {
}
