package br.com.med.voll.api.service.consulta.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarConflitoMesmoHorarioConsulta implements ValidadorAgendamentoDeConsulta{

    private final ConsultaRepository repository;

    public ValidarConflitoMesmoHorarioConsulta(ConsultaRepository repository){
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsultaDto dados){
        var possuirConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (possuirConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário!");
        }
    }
}
