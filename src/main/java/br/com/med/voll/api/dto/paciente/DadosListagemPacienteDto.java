package br.com.med.voll.api.dto.paciente;

public record DadosListagemPacienteDto(

        Long id,
        String nome,
        String email,
        String cpf

) {
}
