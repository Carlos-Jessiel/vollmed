package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAgendamento implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
