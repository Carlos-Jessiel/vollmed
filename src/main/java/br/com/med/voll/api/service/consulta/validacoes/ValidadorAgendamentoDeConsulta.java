package br.com.med.voll.api.service.consulta.validacoes;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta{

    void validar(DadosAgendamentoConsultaDto dados);
}
