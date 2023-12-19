package br.com.med.voll.api.controller;

import br.com.med.voll.api.infra.autenticacao.AutenticacaoService;
import br.com.med.voll.api.infra.autenticacao.dto.DadosAutenticacaoDTO;
import br.com.med.voll.api.infra.security.DadosTokenJwtDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Controller - Login")
@RestController
@RequestMapping(path = "/voll-med", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService service;

    @PostMapping("/login")
    @Operation(summary = "Efetua o login passando os dados de um usuario cadastrado para geração do token de acesso.")
    public ResponseEntity<DadosTokenJwtDTO> efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados){
        return service.executePostLogin(dados);
    }

    @PostMapping("/cadastro")
    @Operation(summary = "Efetua o cadastro de um usuario.")
    public ResponseEntity<DadosAutenticacaoDTO> efetuarCadastro(@RequestBody @Valid DadosAutenticacaoDTO dados){
        return service.executePostCadastro(dados);
    }
}
