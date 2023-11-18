package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PacienteService {

    ResponseEntity<DadosDetalhamentoPacienteDTO> executePost(DadosCadastroPacienteDTO dados);

    ResponseEntity<Page<DadosListagemPacienteDTO>> executeGetAll(Pageable paginacao);

    ResponseEntity<DadosDetalhamentoPacienteDTO> executePut(DadosAtualizacaoPacienteDTO dados);
    
    ResponseEntity executeDelete(Long id);

    ResponseEntity<DadosDetalhamentoPacienteDTO> executeGetOne(Long id);
}
