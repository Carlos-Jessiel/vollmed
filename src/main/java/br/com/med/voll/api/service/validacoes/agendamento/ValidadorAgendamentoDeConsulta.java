package br.com.med.voll.api.service.validacoes.agendamento;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta{

    void validar(DadosAgendamentoConsultaDto dados);
}
