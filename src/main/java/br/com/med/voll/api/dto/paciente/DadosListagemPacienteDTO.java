package br.com.med.voll.api.dto.paciente;

public record DadosListagemPacienteDTO(

        Long id,
        String nome,
        String email,
        String cpf

) {
}
