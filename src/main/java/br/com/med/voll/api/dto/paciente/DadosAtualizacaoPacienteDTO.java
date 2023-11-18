package br.com.med.voll.api.dto.paciente;

import br.com.med.voll.api.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoPacienteDTO(

        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        DadosEnderecoDTO endereco

) {
}
