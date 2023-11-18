package br.com.med.voll.api.provider;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.model.paciente.Paciente;

public class PacienteProvider {

    public static Paciente getEntity() {
        return new Paciente(
                1L,
                "nome",
                "email@email.com",
                "12345678910",
                "65208318083",
                Boolean.TRUE,
                EnderecoProvider.getEntity()
        );
    }

    public static DadosCadastroPacienteDTO getDTO() {
        return new DadosCadastroPacienteDTO(
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getCpf(),
                EnderecoProvider.getDTO()
        );
    }

    public static DadosAtualizacaoPacienteDTO getAtualizarDTO() {
        return new DadosAtualizacaoPacienteDTO(
                1L,
                "NOME",
                "11111111111",
                null
        );
    }

    public static DadosDetalhamentoPacienteDTO getDetalhamentoDTO() {
        return new DadosDetalhamentoPacienteDTO(
                getEntity().getId(),
                getEntity().getNome(),
                getEntity().getEmail(),
                getEntity().getTelefone(),
                getEntity().getCpf(),
                EnderecoProvider.getDTO()
        );
    }

    public static DadosDetalhamentoPacienteDTO getPacienteAtualizadoDTO() {
        return new DadosDetalhamentoPacienteDTO(
                1L,
                getAtualizarDTO().nome(),
                getEntity().getEmail(),
                getAtualizarDTO().telefone(),
                getEntity().getCpf(),
                EnderecoProvider.getDTO()
        );
    }
}
