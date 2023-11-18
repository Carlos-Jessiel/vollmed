package br.com.med.voll.api.mapper;

import br.com.med.voll.api.dto.consulta.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.consulta.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.model.consulta.Consulta;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.model.paciente.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ConsultaMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultaMapper {

    @Mapping(target = "id", ignore = true)
    Consulta toEntity(DadosAgendamentoConsultaDTO dto, Medico medico, Paciente paciente);

    DadosDetalhamentoConsultaDTO toDTO(Consulta entity);
}
