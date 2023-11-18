package br.com.med.voll.api.mapper;

import br.com.med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.model.paciente.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PacienteMapper {


    @Mapping(target = "ativo", constant = "true")
    Paciente toEntity(DadosCadastroPacienteDTO dto);

    DadosDetalhamentoPacienteDTO toDTO(Paciente dto);

    Paciente atualizar(DadosAtualizacaoPacienteDTO dto, @MappingTarget Paciente entity);

    DadosListagemPacienteDTO toListDTO(Paciente entity);
}
