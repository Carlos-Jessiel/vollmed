package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.model.dto.consulta.DadosCancelamentoConsultaDTO;
import br.com.med.voll.api.model.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.mapper.ConsultaMapper;
import br.com.med.voll.api.model.entities.Especialidade;
import br.com.med.voll.api.model.entities.Medico;
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

import static br.com.med.voll.api.infra.DefaultMessage.MUST_BE_FILLED;
import static br.com.med.voll.api.infra.DefaultMessage.NOT_FOUND;
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

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsultaDTO> executePost(Long idMedico, Long idPaciente,
                                                                    DadosRequestDTO dto) {
        validadores.forEach(v -> v.validar(idMedico, idPaciente, dto));

        var medico = medicoRepository.findAtivoById(idMedico)
                .orElse(escolherMedico(ofNullable(
                        dto.especialidade()).orElseThrow(
                        () -> new ValidacaoException(MUST_BE_FILLED.getMensagem())), dto.data()));

        var paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND.getMensagem()));

        return ResponseEntity.ok().body(mapper.toDTO(consultaRepository.save(mapper.toEntity(dto, medico, paciente))));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(Long idConsulta, DadosCancelamentoConsultaDTO dto) {
        validadorCancelamento.forEach(v -> v.validar(idConsulta, dto));

        consultaRepository
                .findById(idConsulta)
                .get()
                .setMotivoCancelamento(dto.motivoCancelamento());

        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsultaDTO> executePut(Long idConsulta, Long idMedico, Long idPaciente,
                                                                   DadosRequestDTO dto) {
        validadores.forEach(v -> v.validar(idMedico, idPaciente, dto));

        var consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new ValidacaoException(NOT_FOUND.getMensagem()));

        var medico = medicoRepository.findAtivoById(idMedico)
                .orElse(escolherMedico(ofNullable(
                        dto.especialidade()).orElseThrow(
                        () -> new ValidacaoException(MUST_BE_FILLED.getMensagem())), dto.data()));

        consulta.setMedico(medico);
        consulta.setData(dto.data());

        return ResponseEntity.ok().body(mapper.toDTO(consulta));
    }

    @Override
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetMedico(Long id, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(consultaRepository
                        .findAllConsultaByMedicoId(id, paginacao)
                        .map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<Page<DadosDetalhamentoConsultaDTO>> executeGetPaciente(Long id, Pageable paginacao) {
        return ResponseEntity
                .ok()
                .body(consultaRepository
                        .findAllConsultaByPacienteId(id, paginacao)
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
                .orElseThrow(() -> new ValidacaoException(
                        NOT_FOUND.getMensagem("Não há médicos disponiveis para data selecionada.")));
    }
}
