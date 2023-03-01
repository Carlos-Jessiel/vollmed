package br.com.med.voll.api.service.medico;


import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoService {

    ResponseEntity execute(DadosCadastroMedicoDto dados);

    ResponseEntity<Page<DadosListagemMedicoDto>> execute(Pageable paginacao);

    ResponseEntity execute(DadosAtualizacaoMeditoDto dados);
}
