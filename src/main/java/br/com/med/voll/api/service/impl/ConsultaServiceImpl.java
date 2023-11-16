package br.com.med.voll.api.service.impl;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.agendamento.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.cancelamento.DadosCancelamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.model.consulta.Consulta;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.ConsultaService;
import br.com.med.voll.api.service.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import br.com.med.voll.api.service.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    private final List<ValidadorAgendamentoDeConsulta> validadores;

    private final List<ValidadorCancelamentoDeConsulta> validadorCancelamento;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository,
                               MedicoRepository medicoRepository,
                               PacienteRepository pacienteRepository,
                               List<ValidadorAgendamentoDeConsulta> validadores,
                               List<ValidadorCancelamentoDeConsulta> validadorCancelamento){
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
        this.validadorCancelamento = validadorCancelamento;
    }

    @Override
    @Transactional
    public ResponseEntity executePost(DadosAgendamentoConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medicoModel = escolherMedico(dados);
        if (medicoModel == null){
            throw new ValidacaoException("Não existe médico disponível para a data informada!");
        }
        var pacienteModel = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new Consulta(null, medicoModel, pacienteModel, dados.data(), null);

        consultaRepository.save(consulta);

        return ResponseEntity.ok(new DadosDetalhamentoConsultaDTO(consulta));
    }

    @Override
    @Transactional
    public ResponseEntity executeDelete(DadosCancelamentoConsultaDto dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta não existe!");
        }

        validadorCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());

        return ResponseEntity.noContent().build();
    }



    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }
}
