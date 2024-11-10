package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.CargoValidartionAnnotation;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionCargo implements ConstraintValidator<CargoValidartionAnnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(CargoEnum.existe(value)){
            return true;
        }
        StringBuilder texto = new StringBuilder();
        texto.append("El cargo ingresado no es valido, coloca alguno de los disponibles: ");
        for(CargoEnum cargo : CargoEnum.values()){
            texto.append(cargo.name()).append("-").append(cargo.getDescripcion()).append(", ");
        }
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(
                texto.toString()
        ).addConstraintViolation();
        return false;
    }
}
