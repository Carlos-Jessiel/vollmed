package br.com.med.voll.api.service.medico;


import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoService {

    ResponseEntity executePost(DadosCadastroMedicoDto dados);

    ResponseEntity<Page<DadosListagemMedicoDto>> executeGetAll(Pageable paginacao);

    ResponseEntity executePut(DadosAtualizacaoMeditoDto dados);

    ResponseEntity executeDelete(Long id);

    ResponseEntity executeGetOne(Long id);
}
