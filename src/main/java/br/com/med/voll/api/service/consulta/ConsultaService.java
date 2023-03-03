package br.com.med.voll.api.service.consulta;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ConsultaService {

    ResponseEntity executePost(@Valid DadosAgendamentoConsultaDto dados);

    ResponseEntity executeDelete(DadosCancelamentoConsultaDto dados);
}
