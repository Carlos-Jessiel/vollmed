package br.com.med.voll.api.dto.endereco;

import br.com.med.voll.api.model.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record DadosEnderecoDTO(

        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        @NotBlank
        String numero

) {
    public static Endereco construirModel(DadosEnderecoDTO dados) {
            var modelEndereco = new Endereco();
            modelEndereco.setBairro(dados.bairro);
            modelEndereco.setUf(dados.uf);
            modelEndereco.setCep(dados.cep);
            modelEndereco.setCidade(dados.cidade);
            modelEndereco.setNumero(dados.numero);
            modelEndereco.setComplemento(dados.complemento);
            modelEndereco.setLogradouro(dados.logradouro);

            return modelEndereco;
    }
}