package br.com.med.voll.api.dto.paciente;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.model.endereco.Endereco;
import br.com.med.voll.api.model.paciente.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPacienteDto(

        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
    public static Paciente construirModel(DadosCadastroPacienteDto dados) {
        Endereco modelEndereco = DadosEndereco.construirModel(dados.endereco);

        var model = new Paciente();
        model.setNome(dados.nome);
        model.setEmail(dados.email);
        model.setTelefone(dados.telefone);
        model.setCpf(dados.cpf);
        model.setAtivo(true);
        model.setEndereco(modelEndereco);

        return model;
    }
}
