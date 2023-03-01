package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;

public record DadosListagemMedicoDto(

        String nome,
        String email,
        String crm,
        Especialidade especialidade

) {
    public DadosListagemMedicoDto(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
