package br.com.med.voll.api.model.dto.paciente;

import br.com.med.voll.api.model.dto.endereco.DadosEnderecoDTO;

public record DadosDetalhamentoPacienteDTO(

        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEnderecoDTO endereco

) {
}
