package br.com.med.voll.api.service;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.model.dto.consulta.DadosCancelamentoConsultaDTO;
import br.com.med.voll.api.model.dto.consulta.DadosDetalhamentoConsultaDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ConsultaService {

    ResponseEntity<DadosDetalhamentoConsultaDTO> executePost(Long idMedico, Long idPaciente,
                                                             @Valid DadosRequestDTO dados);

    ResponseEntity<DadosCancelamentoConsultaDTO> executeDelete(Long idConsulta, @Valid DadosCancelamentoConsultaDTO dados);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetMedico(Long id, Pageable paginacao);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetPaciente(Long id, Pageable paginacao);

    ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGet(Pageable paginacao);

    ResponseEntity<DadosDetalhamentoConsultaDTO> executePut(Long idConsulta, Long idMedico, Long idPaciente, DadosRequestDTO dto);
}
