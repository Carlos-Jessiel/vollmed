package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.provider.PacienteProvider;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerTest {

    private static final String ROTA = "/pacientes";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PacienteServiceImpl pacienteService;

    @Autowired
    private JacksonTester<DadosCadastroPacienteDTO> dadosCadastroPacienteJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoPacienteDTO> dadosDetalhamentoPacienteJSON;

    @Autowired
    private JacksonTester<DadosAtualizacaoPacienteDTO> dadosAtualizacaoPacienteJSON;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 201 indicando cadastro efetuado com sucesso")
    @WithMockUser
    void cadastrar_Cenario1() throws Exception {
        var dadosCadastroPaciente = PacienteProvider.getDTO();
        var dadosDetalhamentoPaciente = PacienteProvider.getDetalhamentoDTO();

        when(pacienteService.executePost(dadosCadastroPaciente))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dadosDetalhamentoPaciente));

        var response =
                mvc.perform(post(ROTA)
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
                mvc.perform(post(ROTA))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria atualizar um registro de pacientes")
    @WithMockUser
    void atualizar_Cenario1() throws Exception {
        var dtoAtualizcao = PacienteProvider.getAtualizarDTO();
        var dadosDetalhamento = PacienteProvider.getPacienteAtualizadoDTO();

        when(pacienteService.executePut(dtoAtualizcao))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(dadosDetalhamento));

        var response = mvc.perform(put(ROTA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoPacienteJSON.write(
                                dtoAtualizcao
                        ).getJson()))
                .andReturn()
                .getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoPacienteJSON
                .write(dadosDetalhamento)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200")
    @WithMockUser
    void deletar_Cenario1() throws Exception {
        Long id = 1L;
        when(pacienteService.executeDelete(id))
                .thenReturn(ResponseEntity
                        .ok()
                        .build());

        var response =
                mvc.perform(delete(ROTA + "/" + id))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 ao consultar um registro")
    @WithMockUser
    void detalhar_Cenario1() throws Exception {
        var responseEsperado = PacienteProvider.getDetalhamentoDTO();

        Long id = 1L;
        when(pacienteService.executeGetOne(id))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(responseEsperado));

        var response =
                mvc.perform(get(ROTA + "/" + id))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoPacienteJSON
                .write(responseEsperado)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}