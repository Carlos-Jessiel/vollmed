package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosCancelamentoConsultaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ConsultaService {

    ResponseEntity<DadosDetalhamentoConsultaDTO> executePost(@Valid DadosAgendamentoConsultaDTO dados);

    ResponseEntity<DadosCancelamentoConsultaDto> executeDelete(@Valid DadosCancelamentoConsultaDto dados);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetMedico(Long id, Pageable paginacao);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetPaciente(Long id, Pageable paginacao);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGet(Pageable paginacao);

    ResponseEntity<DadosDetalhamentoConsultaDTO> executePut(Long id, DadosAgendamentoConsultaDTO dto);
}
