package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.model.endereco.Endereco;
import br.com.med.voll.api.model.medico.Especialidade;

public record DadosDetalhamentoMedicoDto(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
}
