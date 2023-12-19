package br.com.med.voll.api.mapper;

import br.com.med.voll.api.infra.autenticacao.dto.DadosAutenticacaoDTO;
import br.com.med.voll.api.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {

    Usuario toEntity(DadosAutenticacaoDTO dto);

    DadosAutenticacaoDTO toDTO(Usuario entity);
}
