package br.com.med.voll.api.provider;

import br.com.med.voll.api.model.dto.consulta.*;
import br.com.med.voll.api.model.entities.Consulta;
import br.com.med.voll.api.model.entities.Especialidade;
import br.com.med.voll.api.service.validacoes.cancelamento.MotivoCancelamento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultaProvider {

    private static final LocalDateTime DATE = LocalDateTime.of(
            LocalDate.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth().plus(1), DayOfWeek.SUNDAY.getValue()),
            LocalTime.of(11, 50));

    public static Consulta getEntity() {
        return new Consulta(
                1L,
                MedicoProvider.getEntity(),
                PacienteProvider.getEntity(),
                DATE,
                null
        );
    }

    public static DadosRequestDTO getDTO() {
        return new DadosRequestDTO(
                DATE,
                Especialidade.ORTOPEDIA
        );
    }

    public static DadosDetalhamentoConsultaDTO getDetalhamentoDTO() {
        return new DadosDetalhamentoConsultaDTO(
                getEntity().getId(),
                getMedicoConsultaDTO(),
                getPacienteConsultaDTO(),
                DATE
        );
    }

    public static DetalhamentoConsultaMedicoDTO getMedicoConsultaDTO() {
        return new DetalhamentoConsultaMedicoDTO(
                MedicoProvider.getEntity().getId(),
                MedicoProvider.getEntity().getNome(),
                MedicoProvider.getEntity().getCrm(),
                MedicoProvider.getEntity().getEspecialidade()
        );
    }

    public static DetalhamentoConsultaPacienteDTO getPacienteConsultaDTO() {
        return new DetalhamentoConsultaPacienteDTO(
                PacienteProvider.getEntity().getId(),
                PacienteProvider.getEntity().getNome(),
                PacienteProvider.getEntity().getCpf()
        );
    }

    public static DadosCancelamentoConsultaDTO getCancelamentoDTO() {
        return new DadosCancelamentoConsultaDTO(
                MotivoCancelamento.OUTROS
        );
    }

    public static DadosRequestDTO getAtualizacaoDTO() {
        return new DadosRequestDTO(
                DATE.plusHours(1),
                Especialidade.ORTOPEDIA
        );
    }

    public static DadosDetalhamentoConsultaDTO getConsultaAtualizadaDTO() {
        return new DadosDetalhamentoConsultaDTO(
                100L,
                ConsultaProvider.getMedicoConsultaDTO(),
                ConsultaProvider.getPacienteConsultaDTO(),
                getAtualizacaoDTO().data()
        );
    }
}
