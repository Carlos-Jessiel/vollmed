package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    private final PacienteRepository repository;

    public ValidarPacienteAtivo(PacienteRepository repository){
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsultaDTO dados){
        var pacienteEstaAvivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAvivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo!");
        }
    }
}
