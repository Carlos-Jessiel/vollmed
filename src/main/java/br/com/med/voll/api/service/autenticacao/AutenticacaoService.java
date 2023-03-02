package br.com.med.voll.api.service.autenticacao;

import br.com.med.voll.api.dto.autenticacao.DadosAutenticacaoDto;
import org.springframework.http.ResponseEntity;

public interface AutenticacaoService {
    ResponseEntity execute(DadosAutenticacaoDto dados);
}
