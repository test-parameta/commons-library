package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre las entidades {@link EmpleadoEntity} y los DTOs {@link EmpleadoDTO}.
 * <p>
 * Utiliza MapStruct para generar automáticamente las implementaciones de los métodos de mapeo.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    /**
     * Convierte una entidad {@link EmpleadoEntity} a un objeto DTO {@link EmpleadoDTO}.
     *
     * @param entity la entidad {@link EmpleadoEntity} a convertir.
     * @return el objeto {@link EmpleadoDTO} correspondiente.
     */
    EmpleadoDTO entityToDto(EmpleadoEntity entity);

    /**
     * Convierte un objeto DTO {@link EmpleadoDTO} a una entidad {@link EmpleadoEntity}.
     *
     * @param dto el objeto {@link EmpleadoDTO} a convertir.
     * @return la entidad {@link EmpleadoEntity} correspondiente.
     */
    EmpleadoEntity dtoToEntity(EmpleadoDTO dto);

    /**
     * Convierte una lista de entidades {@link EmpleadoEntity} a una lista de objetos DTO {@link EmpleadoDTO}.
     *
     * @param entity la lista de entidades {@link EmpleadoEntity} a convertir.
     * @return la lista de objetos {@link EmpleadoDTO} correspondiente.
     */
    List<EmpleadoDTO> listEntityToListDto(List<EmpleadoEntity> entity);

    /**
     * Convierte una lista de objetos DTO {@link EmpleadoDTO} a una lista de entidades {@link EmpleadoEntity}.
     *
     * @param dto la lista de objetos {@link EmpleadoDTO} a convertir.
     * @return la lista de entidades {@link EmpleadoEntity} correspondiente.
     */
    List<EmpleadoEntity> listDtoToListEntity(List<EmpleadoDTO> dto);
}
