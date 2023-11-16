package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    private final MedicoRepository repository;

    public ValidarMedicoAtivo(MedicoRepository repository){
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoEstadoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstadoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo!");
        }
    }
}
