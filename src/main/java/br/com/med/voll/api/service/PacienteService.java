package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PacienteService {

    ResponseEntity executePost(DadosCadastroPacienteDto dados);

    ResponseEntity<Page<DadosListagemPacienteDto>> executeGetAll(Pageable paginacao);

    ResponseEntity executePut(DadosAtualizacaoPacienteDto dados);
    
    ResponseEntity executeDelete(Long id);

    ResponseEntity executeGetOne(Long id);
}
