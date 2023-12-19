package br.com.med.voll.api.infra.autenticacao;

import br.com.med.voll.api.infra.autenticacao.dto.DadosAutenticacaoDTO;
import br.com.med.voll.api.infra.security.DadosTokenJwtDTO;
import br.com.med.voll.api.infra.security.TokenService;
import br.com.med.voll.api.mapper.UsuarioMapper;
import br.com.med.voll.api.model.entities.Usuario;
import br.com.med.voll.api.repository.UsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements UserDetailsService, AutenticacaoService {

    private final UsuarioRepository repository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioMapper mapper;

    public AutenticacaoServiceImpl(UsuarioRepository repository,
                                   @Lazy AuthenticationManager manager,
                                   TokenService tokenService, UsuarioMapper mapper) {
        this.repository = repository;
        this.manager = manager;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    @Override
    public ResponseEntity<DadosTokenJwtDTO> executePostLogin(DadosAutenticacaoDTO dados) {
        var authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()));

        return ResponseEntity.ok(
                DadosTokenJwtDTO.builder()
                        .token(tokenService.gerarToken((Usuario) authentication.getPrincipal()))
                        .build());
    }

    @Override
    public ResponseEntity<DadosAutenticacaoDTO> executePostCadastro(DadosAutenticacaoDTO dados) {
        return ResponseEntity.ok()
                .body(mapper.toDTO(repository.save(mapper.toEntity(dados))));
    }
}
