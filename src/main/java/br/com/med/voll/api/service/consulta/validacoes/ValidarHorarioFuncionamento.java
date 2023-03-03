package br.com.med.voll.api.service.consulta.validacoes;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsultaDto dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        if (domingo){
            throw new ValidacaoException("Consulta deve ser marcada de segunda a sabado!");
        }

        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if (antesAbertura || depoisFechamento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
