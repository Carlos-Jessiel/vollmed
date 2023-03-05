package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.service.medico.MedicoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicoServiceImpl medicoService;

    @Autowired
    private JacksonTester<DadosCadastroMedicoDto> dadosCadastroMedicoJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedicoDto> dadosDetalhamentoMedicoJSON;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 204 indicando cadastro efetuado com sucesso")
    @WithMockUser
    void cadastrar_Cenario1() throws Exception {
        var endereco = DadosEndereco.construirModel(dadosEndereco());
        var dadosDetalhamentoMedico = new DadosDetalhamentoMedicoDto(
                null,
                "Nome",
                "email@voll.med",
                "51900000000",
                "000000",
                Especialidade.CARDIOLOGIA,
                endereco);
        var dadosCadastroMedico = new DadosCadastroMedicoDto(
                "Nome",
                "email@voll.med",
                "51900000000",
                "000000",
                Especialidade.CARDIOLOGIA,
                dadosEndereco());

        when(medicoService.executePost(dadosCadastroMedico))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dadosDetalhamentoMedico));

        var response =
                mvc.perform(post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroMedicoJSON.write(
                                        dadosCadastroMedico
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
    void cadastrar_Cenario2() throws Exception {
        var response =
                mvc.perform(post("/medicos"))
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void listar() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void desativar() {
    }

    @Test
    void detalhar() {
    }

    private DadosEndereco dadosEndereco(){
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