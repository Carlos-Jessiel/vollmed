package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        return service.executePost(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return service.executeGetAll(paginacao);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        return service.executePut(dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id){
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> detalhar(@PathVariable Long id){
        return service.executeGetOne(id);
    }
}