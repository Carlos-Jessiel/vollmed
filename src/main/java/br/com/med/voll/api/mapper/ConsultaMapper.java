package br.com.med.voll.api.mapper;

import br.com.med.voll.api.model.dto.consulta.DadosRequestDTO;
import br.com.med.voll.api.model.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.model.entities.Consulta;
import br.com.med.voll.api.model.entities.Medico;
import br.com.med.voll.api.model.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ConsultaMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultaMapper {

    @Mapping(target = "id", ignore = true)
    Consulta toEntity(DadosRequestDTO dto, Medico medico, Paciente paciente);

    DadosDetalhamentoConsultaDTO toDTO(Consulta entity);
}
