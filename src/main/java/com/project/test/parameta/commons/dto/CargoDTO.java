package com.project.test.parameta.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CargoDTO {

    private Integer idCargo;

    private String nombreCargo;
}
