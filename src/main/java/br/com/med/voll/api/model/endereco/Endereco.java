package br.com.med.voll.api.model.endereco;

import br.com.med.voll.api.dto.endereco.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null && !dados.logradouro().isEmpty()) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null && !dados.bairro().isEmpty()){
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null && !dados.cep().isEmpty()){
            this.cep = dados.cep();
        }
        if (dados.cidade() != null && !dados.cidade().isEmpty()){
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null && !dados.uf().isEmpty()){
            this.uf = dados.uf();
        }
        if (dados.complemento() != null && !dados.complemento().isEmpty()){
            this.complemento = dados.complemento();
        }
        if (dados.numero() != null && !dados.numero().isEmpty()){
            this.numero = dados.numero();
        }
    }
}
