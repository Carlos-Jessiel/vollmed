package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controller - Médico")
@RestController
@RequestMapping(path = "/voll-med/v1/medicos", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    @Operation(summary = "Cadastrar um Médico.")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados) {
        return service.executePost(dados);
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista paginada contendo todos os Médicos ativos.")
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return service.executeGetAll(paginacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um Médico.")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@PathVariable(value = "id") Long id,
                                                                @RequestBody @Valid DadosAtualizacaoMedicoDTO dados) {
        return service.executePut(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativa um Médico ativo.")
    public ResponseEntity desativar(@PathVariable(value = "id") Long id) {
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados detalhados de um Médico pelo ID.")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> detalhar(@PathVariable(value = "id") Long id) {
        return service.executeGetOne(id);
    }
}