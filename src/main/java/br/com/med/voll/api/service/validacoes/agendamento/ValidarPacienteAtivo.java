package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.infra.DefaultMessage;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    private final PacienteRepository repository;

    public void validar(Long idMedico, Long idPaciente, DadosRequestDTO dados) {
        if (!repository.findAtivoById(idPaciente)) {
            throw new ValidacaoException(DefaultMessage.NOT_FOUND.getMensagem());
        }
    }
}
