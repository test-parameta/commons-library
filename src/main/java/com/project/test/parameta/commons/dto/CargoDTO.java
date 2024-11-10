package com.project.test.parameta.commons.dto;

import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representar la información de un cargo.
 * <p>
 * Contiene los detalles del cargo, incluyendo su identificador único y el nombre del cargo
 * como un enum {@link CargoEnum}.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CargoDTO {

    private Integer idCargo;

    private CargoEnum nombreCargo;
}
