package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.entity.CargoEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre las entidades {@link CargoEntity} y los DTOs {@link CargoDTO}.
 * <p>
 * Utiliza MapStruct para generar automáticamente las implementaciones de los métodos de mapeo.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface CargoMapper {

    /**
     * Convierte una entidad {@link CargoEntity} a un objeto DTO {@link CargoDTO}.
     *
     * @param entity la entidad {@link CargoEntity} a convertir.
     * @return el objeto {@link CargoDTO} correspondiente.
     */
    CargoDTO entityToDto(CargoEntity entity);

    /**
     * Convierte un objeto DTO {@link CargoDTO} a una entidad {@link CargoEntity}.
     *
     * @param dto el objeto {@link CargoDTO} a convertir.
     * @return la entidad {@link CargoEntity} correspondiente.
     */
    CargoEntity dtoToEntity(CargoDTO dto);

    /**
     * Convierte una lista de entidades {@link CargoEntity} a una lista de objetos DTO {@link CargoDTO}.
     *
     * @param entity la lista de entidades {@link CargoEntity} a convertir.
     * @return la lista de objetos {@link CargoDTO} correspondiente.
     */
    List<CargoDTO> listEntityToListDto(List<CargoEntity> entity);

    /**
     * Convierte una lista de objetos DTO {@link CargoDTO} a una lista de entidades {@link CargoEntity}.
     *
     * @param dto la lista de objetos {@link CargoDTO} a convertir.
     * @return la lista de entidades {@link CargoEntity} correspondiente.
     */
    List<CargoEntity> listDtoToListEntity(List<CargoDTO> dto);
}
