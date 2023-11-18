package br.com.med.voll.api.service.validacoes.cancelamento;

import br.com.med.voll.api.dto.consulta.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidarHorarioCancelamentoConsulta implements ValidadorCancelamentoDeConsulta{

    private final ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsultaDto dados){
        var dataConsulta = repository.findById(dados.idConsulta())
                .orElseThrow(() -> new ValidacaoException("Não foi localizado nenhum registro para o id informado."))
                .getData();
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, dataConsulta).toHours();

        if (diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta deve ser cancelada com antecedência mínima de 24 horas!");
        }
    }
}
