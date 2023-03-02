package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.autenticacao.DadosAutenticacaoDto;
import br.com.med.voll.api.repository.UsuarioRepository;
import br.com.med.voll.api.service.autenticacao.AutenticacaoService;
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

    public AutenticacaoServiceImpl(UsuarioRepository repository,
                                   @Lazy AuthenticationManager manager){
        this.repository = repository;
        this.manager = manager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    @Override
    public ResponseEntity execute(DadosAutenticacaoDto dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
