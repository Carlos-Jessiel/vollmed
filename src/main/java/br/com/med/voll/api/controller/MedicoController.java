package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
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
    public ResponseEntity<DadosDetalhamentoMedicoDto> cadastrar(@RequestBody @Valid DadosCadastroMedicoDto dados){
        return service.executePost(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDto>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return service.executeGetAll(paginacao);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedicoDto> atualizar(@RequestBody @Valid DadosAtualizacaoMeditoDto dados){
        return service.executePut(dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id){
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDto> detalhar(@PathVariable Long id){
        return service.executeGetOne(id);
    }
}