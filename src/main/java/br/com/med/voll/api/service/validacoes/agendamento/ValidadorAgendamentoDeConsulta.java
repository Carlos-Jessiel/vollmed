package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta{

    void validar(DadosAgendamentoConsultaDTO dados);
}
