package br.com.med.voll.api.mapper;

import br.com.med.voll.api.model.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.med.voll.api.model.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.model.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PacienteMapper {

    @Mapping(target = "ativo", constant = "true")
    Paciente toEntity(DadosCadastroPacienteDTO dto);

    DadosDetalhamentoPacienteDTO toDTO(Paciente dto);

    @Mapping(source = "id", target = "id")
    Paciente atualizar(Long id, DadosAtualizacaoPacienteDTO dto, @MappingTarget Paciente entity);

    DadosListagemPacienteDTO toListDTO(Paciente entity);
}
