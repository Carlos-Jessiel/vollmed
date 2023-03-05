package br.com.med.voll.api.service.consulta.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    private final PacienteRepository repository;

    public ValidarPacienteAtivo(PacienteRepository repository){
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsultaDto dados){
        var pacienteEstaAvivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAvivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo!");
        }
    }
}
