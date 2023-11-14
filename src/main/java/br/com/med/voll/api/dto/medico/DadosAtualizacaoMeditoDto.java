package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoMeditoDto(

        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        DadosEndereco endereco

) {
}
