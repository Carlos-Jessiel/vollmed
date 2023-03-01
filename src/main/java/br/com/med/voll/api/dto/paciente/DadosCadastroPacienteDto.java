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
        Endereco modelEndereco = new Endereco();
        modelEndereco.setBairro(dados.endereco.bairro());
        modelEndereco.setUf(dados.endereco.uf());
        modelEndereco.setCep(dados.endereco.cep());
        modelEndereco.setCidade(dados.endereco.cidade());
        modelEndereco.setNumero(dados.endereco.numero());
        modelEndereco.setComplemento(dados.endereco.complemento());
        modelEndereco.setLogradouro(dados.endereco.logradouro());

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
