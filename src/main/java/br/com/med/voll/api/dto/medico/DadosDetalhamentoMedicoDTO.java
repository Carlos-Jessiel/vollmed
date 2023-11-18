package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.dto.endereco.DadosEnderecoDTO;
import br.com.med.voll.api.model.medico.Especialidade;

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
