package br.com.med.voll.api.service.paciente;

import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDto;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService{

    private final PacienteRepository repository;

    public PacienteServiceImpl(PacienteRepository repository){
        this.repository = repository;
    }

    @Override
    public ResponseEntity executePost(DadosCadastroPacienteDto dados) {
        var model = DadosCadastroPacienteDto.construirModel(dados);

        repository.save(model);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosDetalhamentoPacienteDto(model));
    }
}
