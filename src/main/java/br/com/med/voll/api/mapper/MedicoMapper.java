package br.com.med.voll.api.mapper;

import br.com.med.voll.api.model.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.model.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.model.entities.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicoMapper {


    @Mapping(target = "ativo", constant = "true")
    Medico toEntity(DadosCadastroMedicoDTO dto);

    DadosDetalhamentoMedicoDTO toDTO(Medico dto);

    @Mapping(source = "id", target = "id")
    Medico atualizar(Long id, DadosAtualizacaoMedicoDTO dto, @MappingTarget Medico entity);

    DadosListagemMedicoDTO toListDTO(Medico entity);
}
