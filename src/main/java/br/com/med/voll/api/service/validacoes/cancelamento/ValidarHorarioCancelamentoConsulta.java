package br.com.med.voll.api.service.validacoes.cancelamento;

import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidarHorarioCancelamentoConsulta implements ValidadorCancelamentoDeConsulta{

    private final ConsultaRepository repository;

    public ValidarHorarioCancelamentoConsulta(ConsultaRepository repository){
        this.repository = repository;
    }

    @Override
    public void validar(DadosCancelamentoConsultaDto dados){
        var dataConsulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, dataConsulta.getData()).toHours();

        if (diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta deve ser cancelada com antecedência mínima de 24 horas!");
        }
    }
}
