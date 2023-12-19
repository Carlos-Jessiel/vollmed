package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.mapper.MedicoMapper;
import br.com.med.voll.api.model.entities.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.service.MedicoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.med.voll.api.infra.DefaultMessage.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> executePost(DadosCadastroMedicoDTO dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(mapper.toDTO(repository.save(mapper.toEntity(dto))));
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page<DadosListagemMedicoDTO>> executeGetAll(Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(repository.findAllByAtivoTrue(paginacao).map(mapper::toListDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> executePut(Long id, DadosAtualizacaoMedicoDTO dto) {
        Medico entity = repository.findById(id)
                .orElseThrow(
                        () -> new ValidacaoException(NOT_FOUND.getMensagem()));

        return ResponseEntity
                .ok()
                .body(mapper.toDTO(mapper.atualizar(id, dto, entity)));
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
    public ResponseEntity<DadosDetalhamentoMedicoDTO> executeGetOne(Long id) {
        return ResponseEntity
                .ok()
                .body(mapper.toDTO(
                        repository.findById(id).orElseThrow(() -> new ValidacaoException(NOT_FOUND.getMensagem()))));
    }
}
