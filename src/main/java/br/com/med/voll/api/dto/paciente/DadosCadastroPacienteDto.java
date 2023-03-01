package br.com.med.voll.api.dto.paciente;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPacienteDto(

        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
