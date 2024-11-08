package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.entity.CargoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    CargoDTO entityToDto(CargoEntity entity);

    CargoEntity dtoToEntity(CargoDTO dto);

    List<CargoDTO> listEntityToListDto(List<CargoEntity> entity);

    List<CargoEntity> listDtoToListEntity(List<CargoDTO> dto);
}
