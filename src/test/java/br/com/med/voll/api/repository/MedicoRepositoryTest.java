package br.com.med.voll.api.repository;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.model.consulta.Consulta;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.model.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Nome", "email@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Nome", "email@gmail.com", "98326065005");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Nome", "email@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade){
        var medico = DadosCadastroMedicoDto.construirModel(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf){
        var paciente = DadosCadastroPacienteDto.construirModel(dadosCadastroPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicoDto dadosMedico(String nome, String email, String crm, Especialidade especialidade){
        return new DadosCadastroMedicoDto(
                nome,
                email,
                "51000000000",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPacienteDto dadosCadastroPaciente(String nome, String email, String cpf){
        return new DadosCadastroPacienteDto(
                nome,
                email,
                "51000000000",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco(
                "rua tal",
                "bairro",
                "00000000",
                "Cidade",
                "TA",
                null,
                null);
    }
}