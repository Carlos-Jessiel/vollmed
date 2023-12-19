package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.infra.DefaultMessage;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarConflitoMesmoHorarioConsulta implements ValidadorAgendamentoDeConsulta {

    private final ConsultaRepository repository;

    public void validar(Long idMedico, Long idPaciente, DadosRequestDTO dados) {
        if (repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(idMedico, dados.data())) {
            throw new ValidacaoException(DefaultMessage.CONFLICT_SAME_TIME.getMensagem());
        }
    }
}
