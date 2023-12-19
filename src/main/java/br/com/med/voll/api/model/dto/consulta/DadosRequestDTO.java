package br.com.med.voll.api.model.dto.consulta;

import br.com.med.voll.api.model.entities.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRequestDTO(

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade

) {
}
