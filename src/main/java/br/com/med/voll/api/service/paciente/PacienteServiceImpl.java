package br.com.med.voll.api.service.paciente;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDto;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    public ResponseEntity executePost(DadosCadastroPacienteDto dados) {
        var model = DadosCadastroPacienteDto.construirModel(dados);

        repository.save(model);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosDetalhamentoPacienteDto(model));
    }

    @Override
    public ResponseEntity<Page<DadosListagemPacienteDto>> executeGetAll(Pageable paginacao) {
        return ResponseEntity.ok().body(repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDto::new));
    }

    @Override
    @Transactional
    public ResponseEntity executePut(DadosAtualizacaoPacienteDto dados) {
        var model = repository.getReferenceById(dados.id());

        model.atualizarInformacoes(dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoPacienteDto(model));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(Long id) {
        var model = repository.getReferenceById(id);
        model.setAtivo(false);
        return ResponseEntity.ok().build();
    }
    
}
