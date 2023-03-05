package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.consulta.agendamento.DadosAgendamentoConsultaDto;
import br.com.med.voll.api.dto.consulta.agendamento.DadosDetalhamentoConsultaDto;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.service.consulta.ConsultaServiceImpl;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsultaDto> dadosAgendamentoConsultaJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsultaDto> dadosDetalhamentoConsultaJSON;

    @MockBean
    private ConsultaServiceImpl consultaService;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400 quando informações estão inválidas")
    @WithMockUser
    void agendar_Cenario1() throws Exception {
        var response =
                mvc.perform(post("/consultas"))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 quando informações estão válidas")
    @WithMockUser
    void agendar_Cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsultaDto(
                null,
                2L,
                1L,
                data);

        when(consultaService.executePost(any()))
                .thenReturn(ResponseEntity.ok()
                        .body(dadosDetalhamento));

        var response =
                mvc.perform(post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJSON.write(
                                        new DadosAgendamentoConsultaDto(2L, 1L, data, especialidade)
                                ).getJson())
                        )
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJSON
                .write(dadosDetalhamento)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}