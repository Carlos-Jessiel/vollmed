package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dados){
        return service.executePost(dados);
    }

    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsultaDto dados){
        return service.executeDelete(dados);
    }
}
