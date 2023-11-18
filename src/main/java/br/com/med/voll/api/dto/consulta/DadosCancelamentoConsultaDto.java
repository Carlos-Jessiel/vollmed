package br.com.med.voll.api.dto.consulta;

import br.com.med.voll.api.service.validacoes.cancelamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDto(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento

) {
}
