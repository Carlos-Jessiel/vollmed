package br.com.med.voll.api.service.consulta.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarConsultaNoMesmoDia implements ValidadorAgendamentoDeConsulta{

    private final ConsultaRepository repository;

    public ValidarConsultaNoMesmoDia(ConsultaRepository repository){
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsultaDto dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia!");
        }
    }
}
