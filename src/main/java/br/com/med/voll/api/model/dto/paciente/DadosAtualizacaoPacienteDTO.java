package br.com.med.voll.api.model.dto.paciente;

import br.com.med.voll.api.model.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoPacienteDTO(

        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        DadosEnderecoDTO endereco

) {
}
