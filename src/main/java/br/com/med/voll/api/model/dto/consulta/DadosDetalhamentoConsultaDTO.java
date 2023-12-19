package br.com.med.voll.api.model.dto.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDTO(

        Long id,
        DetalhamentoConsultaMedicoDTO medico,
        DetalhamentoConsultaPacienteDTO paciente,
        LocalDateTime data

) {
}
