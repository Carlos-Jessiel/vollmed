package br.com.med.voll.api.service;

import br.com.med.voll.api.mapper.MedicoMapperImpl;
import br.com.med.voll.api.provider.MedicoProvider;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.service.impl.MedicoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
public class MedicoServiceTest {

    @InjectMocks
    private MedicoServiceImpl service;

    @Mock
    private MedicoRepository medicoRepository;

    @Before
    public void init() {
        setField(service, "mapper", new MedicoMapperImpl());
    }

    @Test
    @DisplayName("Deve salvar um Médico")
    public void deveSalvarUmMedico() {
        var dadosCadastro = MedicoProvider.getDTO();
        var medico = MedicoProvider.getEntity();

        doReturn(medico).when(medicoRepository).save(any());

        var response = service.executePost(dadosCadastro);

        verify(medicoRepository).save(argThat(
                register -> medico.getNome().equals(register.getNome()) &&
                        medico.getEmail().equals(register.getEmail()) &&
                        medico.getTelefone().equals(register.getTelefone()) &&
                        medico.getCrm().equals(register.getCrm()) &&
                        medico.getEspecialidade().equals(register.getEspecialidade()) &&
                        medico.getEndereco().equals(register.getEndereco())
        ));

        assertEquals(medico.getNome(), response.getBody().nome());
        assertEquals(medico.getEmail(), response.getBody().email());
        assertEquals(medico.getTelefone(), response.getBody().telefone());
        assertEquals(medico.getCrm(), response.getBody().crm());
        assertEquals(medico.getEspecialidade(), response.getBody().especialidade());

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Deve lista paginada de Médico.")
    public void deveListarMedicos() {
        var medico = MedicoProvider.getEntity();
        var request = PageRequest.of(0, 1);

        doReturn(new PageImpl<>(Collections.singletonList(medico)))
                .when(medicoRepository).findAllByAtivoTrue(request);

        var response = service.executeGetAll(request);

        assertEquals(response.getBody().getTotalElements(), 1);
        assertEquals(response.getBody().getContent().get(0).nome(), medico.getNome());
        assertEquals(response.getBody().getContent().get(0).email(), medico.getEmail());
        assertEquals(response.getBody().getContent().get(0).crm(), medico.getCrm());
        assertEquals(response.getBody().getContent().get(0).especialidade(), medico.getEspecialidade());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve atualizar um Médico.")
    public void deveAtualizarUmMedico() {
        var medico = MedicoProvider.getEntity();
        var dadosAtualizacao = MedicoProvider.getAtualizarDTO();

        doReturn(Optional.of(medico)).when(medicoRepository).findById(medico.getId());

        var response = service.executePut(medico.getId(), dadosAtualizacao);

        assertEquals(response.getBody().nome(), dadosAtualizacao.nome());
        assertEquals(response.getBody().email(), medico.getEmail());
        assertEquals(response.getBody().telefone(), dadosAtualizacao.telefone());
        assertEquals(response.getBody().endereco(), MedicoProvider.getDetalhamentoDTO().endereco());
        assertEquals(response.getBody().especialidade(), medico.getEspecialidade());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve desativar um Médico.")
    public void deveDesativarUmMedico() {
        var medico = MedicoProvider.getEntity();

        doReturn(Optional.of(medico)).when(medicoRepository).findById(medico.getId());

        service.executeDelete(medico.getId());

        verify(medicoRepository).findById(medico.getId());
    }

    @Test
    @DisplayName("Deve retornar o registro de um Médico.")
    public void deveRetornarUmMedico() {
        var medico = MedicoProvider.getEntity();
        var responseEsperado = MedicoProvider.getDetalhamentoDTO();

        doReturn(Optional.of(medico)).when(medicoRepository).findById(medico.getId());

        var response = service.executeGetOne(medico.getId());

        verify(medicoRepository).findById(medico.getId());
        assertEquals(response.getBody(), responseEsperado);
    }
}
