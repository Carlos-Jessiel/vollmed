package br.com.med.voll.api.service.consulta;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaService{

    @Override
    public ResponseEntity executePost(DadosAgendamentoConsultaDto dados) {
        return null;
    }
}
