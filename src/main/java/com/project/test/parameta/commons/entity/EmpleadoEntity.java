package com.project.test.parameta.commons.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Entidad que representa la tabla "empleado" en la base de datos.
 * <p>
 * Almacena la información de los empleados, incluyendo sus datos personales,
 * tipo de documento, cargo, y detalles adicionales.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "empleado", schema = "parameta")
public class EmpleadoEntity {

    /**
     * Código único que identifica al empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 5 caracteres.
     */
    @Id
    @Column(name = "codigo_empleado", nullable = false, length = 5)
    private String codigoEmpleado;

    /**
     * Nombre del empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 50 caracteres.
     */
    @Column(name = "nombre_empleado", nullable = false, length = 50)
    private String nombreEmpleado;

    /**
     * Apellidos del empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 50 caracteres.
     */
    @Column(name = "apellidos_empleado", nullable = false, length = 50)
    private String apellidosEmpleado;

    /**
     * Relación con la entidad {@link TipoDocumentoEntity}.
     * Representa el tipo de documento asociado al empleado.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_documento_fk", nullable = false)
    private TipoDocumentoEntity tipoDocumentoFk;

    /**
     * Número de documento del empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 20 caracteres.
     */
    @Column(name = "numero_documento_empleado", nullable = false, length = 20)
    private String numeroDocumentoEmpleado;

    /**
     * Fecha de nacimiento del empleado.
     * Este campo es obligatorio.
     */
    @Column(name = "fecha_nacimiento_empleado", nullable = false)
    private Date fechaNacimientoEmpleado;

    /**
     * Fecha de vinculación del empleado a la compañía.
     * Este campo es obligatorio.
     */
    @Column(name = "fecha_vinculacion_compania_empleado", nullable = false)
    private Date fechaVinculacionCompaniaEmpleado;

    /**
     * Relación con la entidad {@link CargoEntity}.
     * Representa el cargo asociado al empleado.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cargo_fk", nullable = false)
    private CargoEntity cargoFk;

    /**
     * Salario del empleado.
     * Este campo es obligatorio.
     */
    @Column(name = "salario_empleado", nullable = false)
    private Double salarioEmpleado;

    /**
     * Correo electrónico del empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 80 caracteres.
     */
    @Column(name = "correo_empleado", nullable = false, length = 80)
    private String correoEmpleado;

    /**
     * Contraseña cifrada del empleado.
     * Este campo es obligatorio y tiene una longitud máxima de 100 caracteres.
     */
    @Column(name = "hash_password", nullable = false, length = 100)
    private String hashPassword;
}
