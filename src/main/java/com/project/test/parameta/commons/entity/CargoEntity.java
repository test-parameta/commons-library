package com.project.test.parameta.commons.entity;

import com.project.test.parameta.commons.util.enums.CargoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa la tabla "cargo" en la base de datos.
 * <p>
 * Almacena la información relacionada con los cargos disponibles en la compañía,
 * incluyendo su identificador único y su nombre como un enum {@link CargoEnum}.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "cargo", schema = "parameta")
public class CargoEntity {

    /**
     * Identificador único del cargo.
     * Generado automáticamente mediante estrategia {@link GenerationType#IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    /**
     * Nombre del cargo, almacenado como un valor del enum {@link CargoEnum}.
     * Mapeado como un valor de cadena en la base de datos.
     */
    @Column(name = "nombre_cargo", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private CargoEnum nombreCargo;
}
