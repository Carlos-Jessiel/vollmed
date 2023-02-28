package br.com.med.voll.api.service.medico;


import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import org.springframework.http.ResponseEntity;

public interface MedicoService {

    ResponseEntity execute(DadosCadastroMedicoDto dados);
}
