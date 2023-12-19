package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.model.dto.consulta.DadosCancelamentoConsultaDTO;
import br.com.med.voll.api.model.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.service.ConsultaService;
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

@Tag(name = "Controller - Consulta")
@RestController
@RequestMapping(path = "/voll-med/v1/consultas", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping("/agendar/medico/{idMedico}/paciente/{idPaciente}")
    @Operation(summary = "Agendar uma Consulta.")
    public ResponseEntity<DadosDetalhamentoConsultaDTO> agendar(@PathVariable(value = "idMedico", required = false) Long idMedico,
                                                                @PathVariable("idPaciente") Long idPaciente,
                                                                @RequestBody @Valid DadosRequestDTO dto) {
        return service.executePost(idMedico, idPaciente, dto);
    }

    @DeleteMapping("/cancelar/{idConsulta}")
    @Operation(summary = "Cancelar uma Consulta.")
    public ResponseEntity<DadosCancelamentoConsultaDTO> cancelar(@PathVariable("idConsulta") Long idConsulta,
                                                                 @RequestBody @Valid DadosCancelamentoConsultaDTO dto) {
        return service.executeDelete(idConsulta, dto);
    }

    @PutMapping("/{idConsulta}/medico/{idMedico}/paciente/{idPaciente}")
    @Operation(summary = "Atualizar uma Consulta.")
    public ResponseEntity<DadosDetalhamentoConsultaDTO> atualizarConsulta(@PathVariable(value = "idConsulta") Long idConsulta,
                                                                          @PathVariable(value = "idMedico", required = false) Long idMedico,
                                                                          @PathVariable("idPaciente") Long idPaciente,
                                                                          @RequestBody @Valid DadosRequestDTO dto) {
        return service.executePut(idConsulta, idMedico, idPaciente, dto);
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista paginada contendo todas as consultas ativas.")
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsulta(@PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGet(paginacao);
    }

    @GetMapping("/medicos/{id}")
    @Operation(summary = "Retorna uma lista paginada contendo todas as consultas ativas para o Médico do ID informado.")
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsultaMedico(@PathVariable(value = "id") Long id,
                                                                                     @PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGetMedico(id, paginacao);
    }

    @GetMapping("/pacientes/{id}")
    @Operation(summary = "Retorna uma lista paginada contendo todas as consultas ativas para o Paciente do ID informado.")
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> detalharConsultaPaciente(@PathVariable(value = "id") Long id,
                                                                                       @PageableDefault(sort = {"data"}) Pageable paginacao) {
        return service.executeGetPaciente(id, paginacao);
    }
}
