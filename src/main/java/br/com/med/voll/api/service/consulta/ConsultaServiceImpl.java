package br.com.med.voll.api.service.consulta;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.dto.consulta.DadosDetalhamentoConsultaDto;
import br.com.med.voll.api.infra.execption.ValidacaoException;
import br.com.med.voll.api.model.consulta.Consulta;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService{

    private final ConsultaRepository consultaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    private final List<ValidadorAgendamentoDeConsulta> validadores;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository,
                               MedicoRepository medicoRepository,
                               PacienteRepository pacienteRepository,
                               List<ValidadorAgendamentoDeConsulta> validadores){
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
    }

    @Override
    @Transactional
    public ResponseEntity executePost(DadosAgendamentoConsultaDto dados) {
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

        var consulta = new Consulta(null, medicoModel, pacienteModel, dados.data());

        consultaRepository.save(consulta);

        return ResponseEntity.ok(new DadosDetalhamentoConsultaDto(consulta));
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDto dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }
}
