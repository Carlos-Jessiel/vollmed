package br.com.med.voll.api.dto.paciente;

import br.com.med.voll.api.model.endereco.Endereco;
import br.com.med.voll.api.model.paciente.Paciente;

public record DadosDetalhamentoPacienteDto(

        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco

) {

    public DadosDetalhamentoPacienteDto(Paciente paciente){
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco());
    }
}
