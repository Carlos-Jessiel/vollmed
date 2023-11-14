package br.com.med.voll.api.service.validacoes.cancelamento;

import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsultaDto dados);
}
