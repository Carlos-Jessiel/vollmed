package br.com.med.voll.api.service;


import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoService {

    ResponseEntity<DadosDetalhamentoMedicoDto> executePost(DadosCadastroMedicoDto dados);

    ResponseEntity<Page<DadosListagemMedicoDto>> executeGetAll(Pageable paginacao);

    ResponseEntity<DadosDetalhamentoMedicoDto> executePut(DadosAtualizacaoMeditoDto dados);

    ResponseEntity executeDelete(Long id);

    ResponseEntity<DadosDetalhamentoMedicoDto> executeGetOne(Long id);
}
