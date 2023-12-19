package br.com.med.voll.api.infra.autenticacao;

import br.com.med.voll.api.infra.autenticacao.dto.DadosAutenticacaoDTO;
import br.com.med.voll.api.infra.security.DadosTokenJwtDTO;
import org.springframework.http.ResponseEntity;

public interface AutenticacaoService {

    ResponseEntity<DadosTokenJwtDTO> executePostLogin(DadosAutenticacaoDTO dados);

    ResponseEntity<DadosAutenticacaoDTO> executePostCadastro(DadosAutenticacaoDTO dados);
}
