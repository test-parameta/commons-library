package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {

    TipoDocumentoDTO entityToDto(TipoDocumentoEntity entity);

    TipoDocumentoEntity dtoToEntity(TipoDocumentoDTO dto);

    List<TipoDocumentoDTO> listEntityToListDto(List<TipoDocumentoEntity> entity);

    List<TipoDocumentoEntity> listDtoToListEntity(List<TipoDocumentoDTO> dto);
}
