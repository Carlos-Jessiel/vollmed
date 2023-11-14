package br.com.med.voll.api.provider;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;

public class MedicoProvider {

    public static Medico getEntity() {
        return new Medico(
                null,
                "nome",
                "email@email.com",
                "12345678910",
                "12345",
                Especialidade.CARDIOLOGIA,
                EnderecoProvider.getEntity(),
                Boolean.TRUE
        );
    }

    public static DadosCadastroMedicoDto getDTO() {
        return DadosCadastroMedicoDto.builder()
                .nome(getEntity().getNome())
                .email(getEntity().getEmail())
                .telefone(getEntity().getTelefone())
                .crm(getEntity().getCrm())
                .especialidade(getEntity().getEspecialidade())
                .endereco(EnderecoProvider.getDTO())
                .build();
    }

    public static DadosDetalhamentoMedicoDto getDetalhamentoDTO() {
        return new DadosDetalhamentoMedicoDto(
                getEntity().getId(),
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getCrm(),
                getEntity().getEspecialidade(),
                getEntity().getEndereco()
        );
    }

    public static DadosListagemMedicoDto getListagemDTO() {
        return new DadosListagemMedicoDto(
                getEntity().getId(),
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getEspecialidade()
        );
    }

    public static DadosAtualizacaoMeditoDto getAtualizarDTO() {
        return new DadosAtualizacaoMeditoDto(
                1L,
                "NOME",
                "00000000000",
                null
        );
    }

    public static DadosDetalhamentoMedicoDto getMedicoAtualizadoDTO() {
        return new DadosDetalhamentoMedicoDto(
                1L,
                getAtualizarDTO().nome(),
                getEntity().getEmail(),
                getAtualizarDTO().telefone(),
                getEntity().getCrm(),
                getEntity().getEspecialidade(),
                EnderecoProvider.getEntity()
        );
    }
}
