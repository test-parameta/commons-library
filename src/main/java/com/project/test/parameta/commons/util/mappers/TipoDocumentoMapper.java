package com.project.test.parameta.commons.util.mappers;

import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre las entidades {@link TipoDocumentoEntity} y los DTOs {@link TipoDocumentoDTO}.
 * <p>
 * Utiliza MapStruct para generar automáticamente las implementaciones de los métodos de mapeo.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {

    /**
     * Convierte una entidad {@link TipoDocumentoEntity} a un objeto DTO {@link TipoDocumentoDTO}.
     *
     * @param entity la entidad {@link TipoDocumentoEntity} a convertir.
     * @return el objeto {@link TipoDocumentoDTO} correspondiente.
     */
    TipoDocumentoDTO entityToDto(TipoDocumentoEntity entity);

    /**
     * Convierte un objeto DTO {@link TipoDocumentoDTO} a una entidad {@link TipoDocumentoEntity}.
     *
     * @param dto el objeto {@link TipoDocumentoDTO} a convertir.
     * @return la entidad {@link TipoDocumentoEntity} correspondiente.
     */
    TipoDocumentoEntity dtoToEntity(TipoDocumentoDTO dto);

    /**
     * Convierte una lista de entidades {@link TipoDocumentoEntity} a una lista de objetos DTO {@link TipoDocumentoDTO}.
     *
     * @param entity la lista de entidades {@link TipoDocumentoEntity} a convertir.
     * @return la lista de objetos {@link TipoDocumentoDTO} correspondiente.
     */
    List<TipoDocumentoDTO> listEntityToListDto(List<TipoDocumentoEntity> entity);

    /**
     * Convierte una lista de objetos DTO {@link TipoDocumentoDTO} a una lista de entidades {@link TipoDocumentoEntity}.
     *
     * @param dto la lista de objetos {@link TipoDocumentoDTO} a convertir.
     * @return la lista de entidades {@link TipoDocumentoEntity} correspondiente.
     */
    List<TipoDocumentoEntity> listDtoToListEntity(List<TipoDocumentoDTO> dto);
}
