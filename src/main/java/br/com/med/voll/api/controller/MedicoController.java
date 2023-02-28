package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.service.medico.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroMedicoDto dados){
        return service.execute(dados);
    }

}