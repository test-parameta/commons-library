package com.project.test.parameta.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {

    private String codigoEmpleado;

    private String nombreEmpleado;

    private String apellidosEmpleado;

    private String numeroDocumentoEmpleado;

    private Date fechaNacimientoEmpleado;

    private Date fechaVinculacionEmpleado;

    private CargoDTO cargoFk;

    private TipoDocumentoDTO tipoDocumentoFk;

    private String correoEmpleado;

    private Double salarioEmpleado;

    private String hashPassword;
}
