package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.provider.MedicoProvider;
import br.com.med.voll.api.service.impl.MedicoServiceImpl;
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
class MedicoControllerTest {

    private static final String ROTA = "/voll-med/v1/medicos";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicoServiceImpl medicoService;

    @Autowired
    private JacksonTester<DadosCadastroMedicoDTO> dadosCadastroMedicoJSON;

    @Autowired
    private JacksonTester<DadosAtualizacaoMedicoDTO> dadosAtualizacaoMeditoJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedicoDTO> dadosDetalhamentoMedicoJSON;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 201 indicando cadastro efetuado com sucesso")
    @WithMockUser
    public void cadastrar_Cenario1() throws Exception {
        var dadosDetalhamentoMedico = MedicoProvider.getDetalhamentoDTO();
        var dtoMedico = MedicoProvider.getDTO();

        when(medicoService.executePost(dtoMedico))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dadosDetalhamentoMedico));

        var response =
                mvc.perform(post(ROTA)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroMedicoJSON.write(
                                        dtoMedico
                                ).getJson()))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJSON
                .write(dadosDetalhamentoMedico)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400 quando informações estão inválidas")
    @WithMockUser
    public void cadastrar_Cenario2() throws Exception {
        var response =
                mvc.perform(post(ROTA))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria atualizar um registro de médicos")
    @WithMockUser
    public void atualizar_Cenario1() throws Exception {
        var dtoAtualizcao = MedicoProvider.getAtualizarDTO();
        var dadosDetalhamento = MedicoProvider.getMedicoAtualizadoDTO();

        when(medicoService.executePut(MedicoProvider.getEntity().getId(), dtoAtualizcao))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(dadosDetalhamento));

        var response = mvc.perform(put(ROTA + "/" + MedicoProvider.getEntity().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoMeditoJSON.write(
                                dtoAtualizcao
                        ).getJson()))
                .andReturn()
                .getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoMedicoJSON
                .write(dadosDetalhamento)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200")
    @WithMockUser
    public void deletar_Cenario1() throws Exception {

        when(medicoService.executeDelete(MedicoProvider.getEntity().getId()))
                .thenReturn(ResponseEntity
                        .ok()
                        .build());

        var response =
                mvc.perform(delete(ROTA + "/" + MedicoProvider.getEntity().getId()))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 ao consultar um registro")
    @WithMockUser
    public void detalhar_Cenario1() throws Exception {
        var responseEsperado = MedicoProvider.getDetalhamentoDTO();


        when(medicoService.executeGetOne(MedicoProvider.getEntity().getId()))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(responseEsperado));

        var response =
                mvc.perform(get(ROTA + "/" + MedicoProvider.getEntity().getId()))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoMedicoJSON
                .write(responseEsperado)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}