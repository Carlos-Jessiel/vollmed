package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(Long idMedico, Long idPaciente, DadosRequestDTO dados);
}
