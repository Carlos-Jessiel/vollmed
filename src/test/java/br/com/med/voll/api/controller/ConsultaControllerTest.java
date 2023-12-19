package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.model.dto.consulta.DadosCancelamentoConsultaDTO;
import br.com.med.voll.api.model.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.provider.ConsultaProvider;
import br.com.med.voll.api.provider.MedicoProvider;
import br.com.med.voll.api.provider.PacienteProvider;
import br.com.med.voll.api.service.impl.ConsultaServiceImpl;
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
class ConsultaControllerTest {

    private static final String ROTA = "/voll-med/v1/consultas";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosRequestDTO> dadosAgendamentoConsultaJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsultaDTO> dadosDetalhamentoConsultaJSON;

    @Autowired
    private JacksonTester<DadosCancelamentoConsultaDTO> dadosCancelamentoConsultaJSON;

    @MockBean
    private ConsultaServiceImpl consultaService;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400 quando informações estão inválidas")
    @WithMockUser
    public void agendar_Cenario1() throws Exception {
        var response =
                mvc.perform(post(ROTA +
                        "/agendar/medico/" + MedicoProvider.getEntity().getId() +
                        "/paciente/" + PacienteProvider.getEntity().getId()))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 quando informações estão válidas")
    @WithMockUser
    public void agendar_Cenario2() throws Exception {
        var dadosDetalhamentoConsulta = ConsultaProvider.getDetalhamentoDTO();
        var consultaDTO = ConsultaProvider.getDTO();

        when(consultaService.executePost(
                MedicoProvider.getEntity().getId(),
                PacienteProvider.getEntity().getId(),
                consultaDTO))
                .thenReturn(ResponseEntity.ok()
                        .body(dadosDetalhamentoConsulta));

        var response =
                mvc.perform(post(ROTA +
                                "/agendar/medico/" + MedicoProvider.getEntity().getId() +
                                "/paciente/" + PacienteProvider.getEntity().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJSON.write(
                                        consultaDTO
                                ).getJson())
                        )
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJSON
                .write(dadosDetalhamentoConsulta)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200")
    @WithMockUser
    public void deletar_Cenario1() throws Exception {
        var cancelamentoDTO = ConsultaProvider.getCancelamentoDTO();

        when(consultaService.executeDelete(ConsultaProvider.getEntity().getId(), cancelamentoDTO))
                .thenReturn(ResponseEntity
                        .ok()
                        .build());

        var response =
                mvc.perform(delete(ROTA + "/cancelar/" + ConsultaProvider.getEntity().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCancelamentoConsultaJSON.write(
                                        cancelamentoDTO
                                ).getJson())
                        )
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria atualizar um registro de consultas")
    @WithMockUser
    public void atualizar_Cenario1() throws Exception {
        var dtoAtualizcao = ConsultaProvider.getDTO();
        var dtoDetalhamento = ConsultaProvider.getConsultaAtualizadaDTO();

        when(consultaService.executePut(
                ConsultaProvider.getEntity().getId(),
                MedicoProvider.getEntity().getId(),
                PacienteProvider.getEntity().getId(),
                dtoAtualizcao))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(dtoDetalhamento));

        var response = mvc.perform(put(ROTA +
                        "/" + ConsultaProvider.getEntity().getId() +
                        "/medico/" + MedicoProvider.getEntity().getId() +
                        "/paciente/" + PacienteProvider.getEntity().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJSON.write(
                                dtoAtualizcao
                        ).getJson()))
                .andReturn()
                .getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJSON
                .write(dtoDetalhamento)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}