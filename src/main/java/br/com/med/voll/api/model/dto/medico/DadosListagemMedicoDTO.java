package br.com.med.voll.api.model.dto.medico;

import br.com.med.voll.api.model.entities.Especialidade;

public record DadosListagemMedicoDTO(

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade

) {
}
