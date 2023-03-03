package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.service.consulta.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDto dados){
        return service.executePost(dados);
    }

}
