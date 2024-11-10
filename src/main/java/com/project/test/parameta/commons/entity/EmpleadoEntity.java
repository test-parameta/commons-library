package com.project.test.parameta.commons.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "empleado", schema = "parameta")
public class EmpleadoEntity {
    @Id
    @Column(name = "codigo_empleado", nullable = false, length = 5)
    private String codigoEmpleado;

    @Column(name = "nombre_empleado", nullable = false, length = 50)
    private String nombreEmpleado;

    @Column(name = "apellidos_empleado", nullable = false, length = 50)
    private String apellidosEmpleado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_documento_fk", nullable = false)
    private TipoDocumentoEntity tipoDocumentoFk;

    @Column(name = "numero_documento_empleado", nullable = false, length = 20)
    private String numeroDocumentoEmpleado;

    @Column(name = "fecha_nacimiento_empleado", nullable = false)
    private Date fechaNacimientoEmpleado;

    @Column(name = "fecha_vinculacion_compania_empleado", nullable = false)
    private Date fechaVinculacionCompaniaEmpleado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cargo_fk", nullable = false)
    private CargoEntity cargoFk;

    @Column(name = "salario_empleado", nullable = false)
    private Double salarioEmpleado;

    @Column(name = "correo_empleado", nullable = false, length = 80)
    private String correoEmpleado;

    @Column(name = "hash_password", nullable = false, length = 100)
    private String hashPassword;

}