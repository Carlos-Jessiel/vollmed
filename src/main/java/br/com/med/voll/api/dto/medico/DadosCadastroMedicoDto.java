package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.model.endereco.Endereco;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedicoDto(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco


) {
    public static Medico construirModel(DadosCadastroMedicoDto dados) {
        Endereco modelEndereco = new Endereco();
        modelEndereco.setBairro(dados.endereco.bairro());
        modelEndereco.setUf(dados.endereco.uf());
        modelEndereco.setCep(dados.endereco.cep());
        modelEndereco.setCidade(dados.endereco.cidade());
        modelEndereco.setNumero(dados.endereco.numero());
        modelEndereco.setComplemento(dados.endereco.complemento());
        modelEndereco.setLogradouro(dados.endereco.logradouro());

        Medico model = new Medico();
        model.setAtivo(true);
        model.setNome(dados.nome);
        model.setCrm(dados.crm);
        model.setEmail(dados.email);
        model.setTelefone(dados.telefone);
        model.setEndereco(modelEndereco);
        model.setEspecialidade(dados.especialidade);

        return model;
    }

}