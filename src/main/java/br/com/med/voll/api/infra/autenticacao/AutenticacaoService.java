package br.com.med.voll.api.infra.autenticacao;

import br.com.med.voll.api.infra.autenticacao.dto.DadosAutenticacaoDto;
import org.springframework.http.ResponseEntity;

public interface AutenticacaoService {
    ResponseEntity execute(DadosAutenticacaoDto dados);
}
