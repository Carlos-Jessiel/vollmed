package br.com.med.voll.api.service.validacoes.cancelamento;

import br.com.med.voll.api.dto.consulta.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

import static br.com.med.voll.api.infra.DefaultMessage.MINIMUM_NOTICE;
import static br.com.med.voll.api.infra.DefaultMessage.NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ValidarHorarioCancelamentoConsulta implements ValidadorCancelamentoDeConsulta {

    private final ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsultaDto dados) {
        var dataConsulta = repository.findById(dados.idConsulta())
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND.getMensagem()))
                .getData();
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, dataConsulta).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException(MINIMUM_NOTICE.getMensagem() + " 24 horas.");
        }
    }
}
