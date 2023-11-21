package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static br.com.med.voll.api.infra.DefaultMessage.OUT_OF_SERVICE;

@Component
public class ValidarHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        if (domingo) {
            throw new ValidacaoException(
                    OUT_OF_SERVICE.getMensagem());
        }

        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if (antesAbertura || depoisFechamento) {
            throw new ValidacaoException(
                    OUT_OF_SERVICE.getMensagem("Consulta fora do horário de funcionamento da clínica."));
        }
    }
}
