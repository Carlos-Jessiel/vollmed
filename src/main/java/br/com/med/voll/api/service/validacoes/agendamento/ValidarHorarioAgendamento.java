package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.infra.DefaultMessage;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAgendamento implements ValidadorAgendamentoDeConsulta {

    public void validar(Long idMedico, Long idPaciente, DadosRequestDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        if (Duration.between(agora, dataConsulta).toMinutes() < 30) {
            throw new ValidacaoException(DefaultMessage.MINIMUM_NOTICE.getMensagem() + " 30 minutos.");
        }
    }
}
