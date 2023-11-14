package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.mapper.MedicoMapper;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.service.MedicoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;
    private final MedicoMapper mapper;
    private static final String NOT_FOUND_BY_ID = "Não foi localizado nenhum registro para o id informado.";

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDto> executePost(DadosCadastroMedicoDto dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(mapper.toDTO(repository.save(mapper.toEntity(dto))));
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Page<DadosListagemMedicoDto>> executeGetAll(Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(repository.findAllByAtivoTrue(paginacao).map(mapper::toListDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDto> executePut(DadosAtualizacaoMeditoDto dto) {
        Medico entity = repository.findById(dto.id())
                .orElseThrow(
                        () -> new ValidacaoException(NOT_FOUND_BY_ID));

        return ResponseEntity
                .ok()
                .body(mapper.toDTO(mapper.atualizar(dto, entity)));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND_BY_ID))
                .setAtivo(false);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DadosDetalhamentoMedicoDto> executeGetOne(Long id) {
        return ResponseEntity
                .ok()
                .body(mapper.toDTO(
                        repository.findById(id).orElseThrow(() -> new ValidacaoException(NOT_FOUND_BY_ID))));
    }
}
