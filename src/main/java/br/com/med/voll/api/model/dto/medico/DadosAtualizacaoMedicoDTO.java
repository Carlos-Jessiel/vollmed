package br.com.med.voll.api.model.dto.medico;

import br.com.med.voll.api.model.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoMedicoDTO(

        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        DadosEnderecoDTO endereco

) {
}
