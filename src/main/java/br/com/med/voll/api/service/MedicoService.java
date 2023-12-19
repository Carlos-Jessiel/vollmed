package br.com.med.voll.api.service;


import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosListagemMedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoService {

    ResponseEntity<DadosDetalhamentoMedicoDTO> executePost(DadosCadastroMedicoDTO dados);

    ResponseEntity<Page<DadosListagemMedicoDTO>> executeGetAll(Pageable paginacao);

    ResponseEntity<DadosDetalhamentoMedicoDTO> executePut(Long id, DadosAtualizacaoMedicoDTO dados);

    ResponseEntity executeDelete(Long id);

    ResponseEntity<DadosDetalhamentoMedicoDTO> executeGetOne(Long id);
}
