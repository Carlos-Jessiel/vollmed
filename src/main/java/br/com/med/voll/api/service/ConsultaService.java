package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ConsultaService {

    ResponseEntity executePost(@Valid DadosAgendamentoConsultaDTO dados);

    ResponseEntity executeDelete(DadosCancelamentoConsultaDto dados);
}
