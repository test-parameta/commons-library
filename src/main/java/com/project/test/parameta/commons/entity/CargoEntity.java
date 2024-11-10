package com.project.test.parameta.commons.entity;

import com.project.test.parameta.commons.util.enums.CargoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cargo", schema = "parameta")
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Column(name = "nombre_cargo", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CargoEnum nombreCargo;

}