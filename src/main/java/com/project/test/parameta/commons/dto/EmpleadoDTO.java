package com.project.test.parameta.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

/**
 * DTO para representar la información de un empleado.
 * <p>
 * Contiene los datos generales del empleado, incluyendo identificadores,
 * información personal, detalles de empleo y credenciales.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EmpleadoDTO {

    private String codigoEmpleado;

    private String nombreEmpleado;

    private String apellidosEmpleado;

    private String numeroDocumentoEmpleado;

    private Date fechaNacimientoEmpleado;

    private Date fechaVinculacionCompaniaEmpleado;

    private CargoDTO cargoFk;

    private TipoDocumentoDTO tipoDocumentoFk;

    private String correoEmpleado;

    private Double salarioEmpleado;

    private String hashPassword;
}
