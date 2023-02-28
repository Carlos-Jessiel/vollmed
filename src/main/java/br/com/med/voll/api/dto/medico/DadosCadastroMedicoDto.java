package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.endereco.DadosEndereco;
import br.com.med.voll.api.model.medico.Especialidade;

public record DadosCadastroMedicoDto(

        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco


) {
}