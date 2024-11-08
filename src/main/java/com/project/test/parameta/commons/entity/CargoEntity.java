package com.project.test.parameta.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cargo", schema = "parameta")
public class CargoEntity {
    @Id
    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Column(name = "nombre_cargo", nullable = false, length = 50)
    private String nombreCargo;

}