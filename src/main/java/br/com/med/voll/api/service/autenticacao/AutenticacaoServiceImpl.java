package br.com.med.voll.api.service.autenticacao;

import br.com.med.voll.api.dto.autenticacao.DadosAutenticacaoDto;
import br.com.med.voll.api.infra.security.DadosTokenJwtDto;
import br.com.med.voll.api.infra.security.TokenService;
import br.com.med.voll.api.model.usuario.Usuario;
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

    public AutenticacaoServiceImpl(UsuarioRepository repository,
                                   @Lazy AuthenticationManager manager,
                                   TokenService tokenService){
        this.repository = repository;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    @Override
    public ResponseEntity execute(DadosAutenticacaoDto dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwtDto(tokenJWT));
    }
}
