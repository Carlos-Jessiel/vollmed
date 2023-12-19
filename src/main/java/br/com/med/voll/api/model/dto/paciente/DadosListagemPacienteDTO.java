package br.com.med.voll.api.model.dto.paciente;

public record DadosListagemPacienteDTO(

        Long id,
        String nome,
        String email,
        String cpf

) {
}
