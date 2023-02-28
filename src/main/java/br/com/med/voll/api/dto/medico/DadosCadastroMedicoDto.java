package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.endereco.DadosEndereco;
import br.com.med.voll.api.endereco.Endereco;
import br.com.med.voll.api.model.medico.Especialidade;
import br.com.med.voll.api.model.medico.Medico;

public record DadosCadastroMedicoDto(

        String nome,
        String email,
        String crm,
        Especialidade especialidade,
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
        model.setNome(dados.nome);
        model.setCrm(dados.crm);
        model.setEmail(dados.email);
        model.setEndereco(modelEndereco);
        model.setEspecialidade(dados.especialidade);

        return model;
    }

}