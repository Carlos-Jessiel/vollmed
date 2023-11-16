package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        return service.executePost(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.executeGetAll(paginacao);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoPacienteDTO> atualizar(@RequestBody DadosAtualizacaoPacienteDTO dados) {
        return service.executePut(dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id) {
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> detalhar(@PathVariable Long id) {
        return service.executeGetOne(id);
    }
}
