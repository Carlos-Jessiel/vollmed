package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsultaDTO> agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dto) {
        return service.executePost(dto);
    }

    @DeleteMapping
    public ResponseEntity<DadosCancelamentoConsultaDto> cancelar(@RequestBody @Valid DadosCancelamentoConsultaDto dto) {
        return service.executeDelete(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosDetalhamentoConsultaDTO> atualizarConsulta(@PathVariable("id") Long id,
                                                                          @RequestBody @Valid DadosAgendamentoConsultaDTO dto) {
        return service.executePut(id, dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsultaMedico(@PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGet(paginacao);
    }

    @GetMapping("medicos/{id}")
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsultaMedico(@PathVariable("id") Long id,
                                                                                     @PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGetMedico(id, paginacao);
    }

    @GetMapping("pacientes/{id}")
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsultaPaciente(@PathVariable("id") Long id,
                                                                                       @PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGetPaciente(id, paginacao);
    }
}
