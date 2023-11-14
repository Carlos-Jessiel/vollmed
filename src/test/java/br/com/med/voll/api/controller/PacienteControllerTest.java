package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDto;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDto;
import br.com.med.voll.api.service.impl.PacienteServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PacienteServiceImpl pacienteService;

    @Autowired
    private JacksonTester<DadosCadastroPacienteDto> dadosCadastroPacienteJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoPacienteDto> dadosDetalhamentoPacienteJSON;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 204 indicando cadastro efetuado com sucesso")
    @WithMockUser
    void cadastrar_Cenario1() throws Exception {
        var endereco = DadosEndereco.construirModel(dadosEndereco());
        var dadosDetalhamentoPaciente = new DadosDetalhamentoPacienteDto(
                null,
                "Nome",
                "email@voll.med",
                "51900000000",
                "66360058405",
                endereco
        );
        var dadosCadastroPaciente = new DadosCadastroPacienteDto(
                "Nome",
                "email@voll.med",
                "51900000000",
                "66360058405",
                dadosEndereco()
        );

        when(pacienteService.executePost(dadosCadastroPaciente))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dadosDetalhamentoPaciente));

        var response =
                mvc.perform(post("/pacientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroPacienteJSON
                                        .write(dadosCadastroPaciente)
                                        .getJson()))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoPacienteJSON
                .write(dadosDetalhamentoPaciente)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400 quando informações estão inválidas")
    @WithMockUser
    void cadastrar_Cenario2() throws Exception {
        var response =
                mvc.perform(post("/pacientes"))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua tal",
                "bairro",
                "00000000",
                "Cidade",
                "TA",
                null,
                "100");
    }
}