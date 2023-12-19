package br.com.med.voll.api.model.dto.consulta;

import br.com.med.voll.api.model.entities.Especialidade;

public record DetalhamentoConsultaMedicoDTO(

        Long id,
        String nome,
        String crm,
        Especialidade especialidade
) {
}
