package com.project.test.parameta.commons.dto;

import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar la información de un tipo de documento.
 * <p>
 * Contiene los detalles del tipo de documento, incluyendo su identificador único
 * y el nombre del tipo representado como un enum {@link TipoDocumentoEnum}.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoDTO {

    /**
     * Identificador único del tipo de documento.
     */
    private Integer idTipoDocumento;

    /**
     * Nombre del tipo de documento, representado como un enum {@link TipoDocumentoEnum}.
     */
    private TipoDocumentoEnum nombreTipoDocumento;
}
