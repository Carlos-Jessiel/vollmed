package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.model.endereco.Endereco;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;

public record DadosDetalhamentoMedicoDto(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedicoDto(Medico medico){
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
