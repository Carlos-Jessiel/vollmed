package br.com.med.voll.api.dto.consulta;

import br.com.med.voll.api.model.medico.Especialidade;

public record DetalhamentoConsultaMedicoDTO(

        Long id,
        String nome,
        String crm,
        Especialidade especialidade
) {
}
