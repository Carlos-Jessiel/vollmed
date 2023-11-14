package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDto;
import br.com.med.voll.api.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacienteDto dados){
        return service.executePost(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return service.executeGetAll(paginacao);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPacienteDto dados){
        return service.executePut(dados);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id) {
        return service.executeDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return service.executeGetOne(id);
    }
}
