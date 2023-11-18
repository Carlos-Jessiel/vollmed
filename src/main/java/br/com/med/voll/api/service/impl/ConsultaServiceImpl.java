package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.mapper.ConsultaMapper;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.ConsultaService;
import br.com.med.voll.api.service.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import br.com.med.voll.api.service.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validadores;
    private final List<ValidadorCancelamentoDeConsulta> validadorCancelamento;
    private final ConsultaMapper mapper;
    private static final String NOT_FOUND_BY_ID = "Não foi localizado nenhum registro para o id informado.";
    private static final String MUST_BE_FILLED = "Especialidade é obrigatória quando médico não for escolhido!";

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsultaDTO> executePost(DadosAgendamentoConsultaDTO dto) {
        validadores.forEach(v -> v.validar(dto));

        var medico = medicoRepository.findAtivoById(dto.idMedico())
                .orElse(escolherMedico(ofNullable(
                        dto.especialidade()).orElseThrow(() -> new ValidacaoException(MUST_BE_FILLED)), dto.data()));

        var paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND_BY_ID));

        return ResponseEntity
                .ok()
                .body(mapper.toDTO(consultaRepository.save(mapper.toEntity(dto, medico, paciente))));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(DadosCancelamentoConsultaDto dto) {
        validadorCancelamento.forEach(v -> v.validar(dto));

        consultaRepository
                .findById(dto.idConsulta())
                .get()
                .setMotivoCancelamento(dto.motivoCancelamento());

        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsultaDTO> executePut(Long id, DadosAgendamentoConsultaDTO dto) {
        validadores.forEach(v -> v.validar(dto));

        var consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND_BY_ID));

        var medico = medicoRepository.findAtivoById(dto.idMedico())
                .orElse(escolherMedico(ofNullable(
                        dto.especialidade()).orElseThrow(() -> new ValidacaoException(MUST_BE_FILLED)), dto.data()));

        consulta.setMedico(medico);
        consulta.setData(dto.data());

        return ResponseEntity.ok().body(mapper.toDTO(consulta));
    }

    @Override
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetMedico(Long id, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(consultaRepository
                        .findAllByMedicoId(id, paginacao)
                        .map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetPaciente(Long id, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(consultaRepository
                        .findAllByPacienteId(id, paginacao)
                        .map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGet(Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(consultaRepository
                        .findAll(paginacao)
                        .map(mapper::toDTO));
    }


    private Medico escolherMedico(Especialidade especialidade, LocalDateTime data) {
        return of(medicoRepository.escolherMedicoAleatorioLivreNaData(especialidade, data))
                .orElseThrow(() -> new ValidacaoException("Não há médicos disponiveis para data selecionada!"));
    }
}
