package br.com.med.voll.api.provider;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import br.com.med.voll.api.model.endereco.Endereco;

public class EnderecoProvider {

    public static DadosEndereco getDTO() {
        return DadosEndereco.builder()
                .logradouro("logradouro")
                .bairro("bairro")
                .cep("00000000")
                .cidade("cidade")
                .uf("uf")
                .complemento("complemento")
                .numero("numero")
                .build();
    }

    public static Endereco getEntity() {
        return new Endereco(
                "logradouro",
                "bairro",
                "00000000",
                "cidade",
                "uf",
                "complemento",
                "numero"
        );
    }
}
