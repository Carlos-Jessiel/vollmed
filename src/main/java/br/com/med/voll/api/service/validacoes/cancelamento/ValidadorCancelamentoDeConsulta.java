package br.com.med.voll.api.service.validacoes.cancelamento;

import br.com.med.voll.api.model.dto.consulta.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(Long idConsulta, DadosCancelamentoConsultaDTO dados);
}
