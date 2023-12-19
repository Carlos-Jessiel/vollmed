package br.com.med.voll.api.model.dto.medico;

import br.com.med.voll.api.model.dto.endereco.DadosEnderecoDTO;
import br.com.med.voll.api.model.entities.Especialidade;

public record DadosDetalhamentoMedicoDTO(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        DadosEnderecoDTO endereco
) {
}
