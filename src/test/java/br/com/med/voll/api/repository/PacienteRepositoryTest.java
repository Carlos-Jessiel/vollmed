package br.com.med.voll.api.repository;

import br.com.med.voll.api.model.entities.Paciente;
import br.com.med.voll.api.provider.PacienteProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria retornar um paciente ativo pelo ID.")
    public void encontrarPacienteComAtivo() {
        var paciente = PacienteProvider.getEntity();

        cadastrarPaciente(paciente);

        assertThat(pacienteRepository.findAtivoById(paciente.getId())).isTrue();
    }

    public void cadastrarPaciente(Paciente paciente) {
        paciente.setId(null);
        em.persist(paciente);
    }
}
