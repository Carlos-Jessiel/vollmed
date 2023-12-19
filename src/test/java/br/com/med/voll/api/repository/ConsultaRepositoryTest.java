package br.com.med.voll.api.repository;

import br.com.med.voll.api.model.entities.Consulta;
import br.com.med.voll.api.model.entities.Medico;
import br.com.med.voll.api.model.entities.Paciente;
import br.com.med.voll.api.provider.ConsultaProvider;
import br.com.med.voll.api.provider.MedicoProvider;
import br.com.med.voll.api.provider.PacienteProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ConsultaRepositoryTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria retornar uma lista paginada contendo todas as Consultas de um Medico.")
    public void encontrarConsultasDeUmMedicoPeloID() {
        var consulta = cadastrarConsulta();

        var register = consultaRepository.findAllConsultaByMedicoId(
                consulta.getMedico().getId(), PageRequest.of(0, 1));

        assertThat(consulta).isEqualTo(register.getContent().get(0));
    }

    @Test
    @DisplayName("Deveria retornar uma lista paginada contendo todas as Consultas de um Paciente.")
    public void encontrarConsultasDeUmPacienteoPeloID() {
        var consulta = cadastrarConsulta();

        var register = consultaRepository.findAllConsultaByPacienteId(
                consulta.getPaciente().getId(), PageRequest.of(0, 1));

        assertThat(consulta).isEqualTo(register.getContent().get(0));
    }

    @Test
    @DisplayName("Deveria retornar true se Medico contem consulta marcada para mesmo horario.")
    public void verificaConflitoConsultaMesmoHorarioCenario1() {
        var consulta = cadastrarConsulta();

        assertThat(consultaRepository
                .existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(consulta.getMedico().getId(), consulta.getData()))
                .isTrue();
    }

    @Test
    @DisplayName("Deveria retornar false se Medico não contem consulta marcada para mesmo horario.")
    public void verificaConflitoConsultaMesmoHorarioCenario2() {
        var consulta = cadastrarConsulta();
        var date = consulta.getData().plusMinutes(60);

        assertThat(consultaRepository
                .existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(consulta.getMedico().getId(), date))
                .isFalse();
    }

    @Test
    @DisplayName("Deveria retornar true se Paciente contem consulta marcada para mesmo dia.")
    public void verificaConflitoConsultaMesmoDiaCenario1() {
        var consulta = cadastrarConsulta();

        assertThat(consultaRepository
                .existsByPacienteIdAndDataBetween(
                        consulta.getPaciente().getId(),
                        consulta.getData().withHour(7),
                        consulta.getData().withHour(18))).isTrue();
    }

    @Test
    @DisplayName("Deveria retornar false se Paciente não contem consulta marcada para mesmo dia.")
    public void verificaConflitoConsultaMesmoDiaCenario2() {
        var consulta = ConsultaProvider.getEntity();

        assertThat(consultaRepository
                .existsByPacienteIdAndDataBetween(
                        consulta.getPaciente().getId(),
                        consulta.getData().withHour(7),
                        consulta.getData().withHour(18))).isFalse();
    }

    public Consulta cadastrarConsulta() {
        var consulta = ConsultaProvider.getEntity();
        consulta.setId(null);
        consulta.setPaciente(cadastrarPaciente());
        consulta.setMedico(cadastrarMedico());
        return em.persist(consulta);
    }

    private Paciente cadastrarPaciente() {
        var paciente = PacienteProvider.getEntity();
        paciente.setId(null);
        return em.persist(paciente);
    }

    private Medico cadastrarMedico() {
        var medico = MedicoProvider.getEntity();
        medico.setId(null);
        return em.persist(medico);
    }
}
