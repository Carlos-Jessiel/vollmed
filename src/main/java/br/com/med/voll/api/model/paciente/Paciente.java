package br.com.med.voll.api.model.paciente;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDto;
import br.com.med.voll.api.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizacaoPacienteDto dados) {
        if (dados.nome() != null && !dados.nome().isEmpty()){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null && dados.telefone().matches("\\d{11}")){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
}
