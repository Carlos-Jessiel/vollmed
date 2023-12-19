package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Controller - Paciente")
@RestController
@RequestMapping(path = "/voll-med/v1/pacientes")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    @Operation(summary = "Cadastrar um Paciente.")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        return service.executePost(dados);
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista paginada contendo todos os Pacientes ativos.")
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return service.executeGetAll(paginacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um paciente pelo ID informado.")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> atualizar(@PathVariable(value = "id") Long id,
                                                                  @RequestBody DadosAtualizacaoPacienteDTO dados) {
        return service.executePut(id, dados);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Desativa um Paciente ativo.")
    public ResponseEntity desativar(@PathVariable(value = "id") Long id) {
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados detalhados de um Paciente pelo ID.")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> detalhar(@PathVariable(value = "id") Long id) {
        return service.executeGetOne(id);
    }
}
