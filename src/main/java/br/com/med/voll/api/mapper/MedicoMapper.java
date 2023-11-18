package br.com.med.voll.api.mapper;

import br.com.med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.model.medico.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicoMapper {


    @Mapping(target = "ativo", constant = "true")
    Medico toEntity(DadosCadastroMedicoDTO dto);

    DadosDetalhamentoMedicoDTO toDTO(Medico dto);

    Medico atualizar(DadosAtualizacaoMedicoDTO dto, @MappingTarget Medico entity);

    DadosListagemMedicoDTO toListDTO(Medico entity);
}
