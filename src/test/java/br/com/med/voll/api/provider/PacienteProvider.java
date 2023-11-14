package br.com.med.voll.api.provider;

import br.com.med.voll.api.model.paciente.Paciente;

public class PacienteProvider {

    public static Paciente getEntity() {
        return new Paciente(
                null,
                "nome",
                "email",
                "telefone",
                "cpf",
                Boolean.TRUE,
                EnderecoProvider.getEntity()
        );
    }
}
