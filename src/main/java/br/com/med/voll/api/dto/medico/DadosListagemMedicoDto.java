package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.model.medico.Especialidade;

public record DadosListagemMedicoDto(

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade

) {
}
