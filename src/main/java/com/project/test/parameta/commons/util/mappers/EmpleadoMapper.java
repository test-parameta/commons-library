package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    EmpleadoDTO entityToDto(EmpleadoEntity entity);

    EmpleadoEntity dtoToEntity(EmpleadoDTO dto);

    List<EmpleadoDTO> listEntityToListDto(List<EmpleadoEntity> entity);

    List<EmpleadoEntity> listDtoToListEntity(List<EmpleadoDTO> dto);
}
