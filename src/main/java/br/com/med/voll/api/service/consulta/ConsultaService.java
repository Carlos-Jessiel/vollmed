package br.com.med.voll.api.service.consulta;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ConsultaService {

    ResponseEntity executePost(@Valid DadosAgendamentoConsultaDto dados);
}
