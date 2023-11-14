package br.com.med.voll.api.mapper;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMeditoDto;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDto;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDto;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDto;
import br.com.med.voll.api.model.medico.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicoMapper {


    @Mapping(target = "ativo", constant = "true")
    Medico toEntity(DadosCadastroMedicoDto dto);

    DadosDetalhamentoMedicoDto toDTO(Medico dto);

    Medico atualizar(DadosAtualizacaoMeditoDto dto, @MappingTarget Medico entity);

    DadosListagemMedicoDto toListDTO(Medico entity);
}
