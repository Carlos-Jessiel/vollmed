package br.com.med.voll.api.provider;

import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.model.entities.Especialidade;
import br.com.med.voll.api.model.entities.Medico;

public class MedicoProvider {

    public static Medico getEntity() {
        return new Medico(
                1L,
                "nome",
                "email@email.com",
                "12345678910",
                "12345",
                Especialidade.CARDIOLOGIA,
                EnderecoProvider.getEntity(),
                Boolean.TRUE
        );
    }

    public static DadosCadastroMedicoDTO getDTO() {
        return new DadosCadastroMedicoDTO(
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getCrm(),
                getEntity().getEspecialidade(),
                EnderecoProvider.getDTO()
        );
    }

    public static DadosDetalhamentoMedicoDTO getDetalhamentoDTO() {
        return new DadosDetalhamentoMedicoDTO(
                getEntity().getId(),
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getCrm(),
                getEntity().getEspecialidade(),
                EnderecoProvider.getDTO()
        );
    }

    public static DadosListagemMedicoDTO getListagemDTO() {
        return new DadosListagemMedicoDTO(
                getEntity().getId(),
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getEspecialidade()
        );
    }

    public static DadosAtualizacaoMedicoDTO getAtualizarDTO() {
        return new DadosAtualizacaoMedicoDTO(
                "NOME",
                "00000000000",
                null
        );
    }

    public static DadosDetalhamentoMedicoDTO getMedicoAtualizadoDTO() {
        return new DadosDetalhamentoMedicoDTO(
                1L,
                getAtualizarDTO().nome(),
                getEntity().getEmail(),
                getAtualizarDTO().telefone(),
                getEntity().getCrm(),
                getEntity().getEspecialidade(),
                EnderecoProvider.getDTO()
        );
    }
}
