package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.mapper.PacienteMapper;
import br.com.med.voll.api.model.paciente.Paciente;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.PacienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.med.voll.api.infra.DefaultMessage.*;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> executePost(DadosCadastroPacienteDTO dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(mapper.toDTO(repository.save(mapper.toEntity(dto))));
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page<DadosListagemPacienteDTO>> executeGetAll(Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(repository.findAllByAtivoTrue(paginacao).map(mapper::toListDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> executePut(DadosAtualizacaoPacienteDTO dto) {
        Paciente entity = repository.findById(dto.id())
                .orElseThrow(
                        () -> new ValidacaoException(NOT_FOUND.getMensagem()));

        return ResponseEntity
                .ok()
                .body(mapper.toDTO(mapper.atualizar(dto, entity)));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND.getMensagem()))
                .setAtivo(false);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DadosDetalhamentoPacienteDTO> executeGetOne(Long id) {
        return ResponseEntity
                .ok()
                .body(mapper.toDTO(
                        repository.findById(id).orElseThrow(
                                () -> new ValidacaoException(NOT_FOUND.getMensagem()))));
    }
}
