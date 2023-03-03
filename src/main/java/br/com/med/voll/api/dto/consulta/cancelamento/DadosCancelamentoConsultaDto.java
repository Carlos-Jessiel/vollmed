package br.com.med.voll.api.dto.consulta.cancelamento;

import br.com.med.voll.api.model.consulta.Consulta;
import br.com.med.voll.api.service.consulta.validacoes.cancelamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDto(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento

) {
}
