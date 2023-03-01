package br.com.med.voll.api.service.paciente;

import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import org.springframework.http.ResponseEntity;

public interface PacienteService {

    ResponseEntity executePost(DadosCadastroPacienteDto dados);
}
