package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {


    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroMedicoDto dados){
        //TODO: desenvolver service para cadastro
        return null;
    }

}