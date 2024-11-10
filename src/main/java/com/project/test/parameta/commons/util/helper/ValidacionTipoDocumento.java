package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.TipoDocumentoValidationAnnotation;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionTipoDocumento implements ConstraintValidator<TipoDocumentoValidationAnnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(TipoDocumentoEnum.existe(value)) {
            return true;
        }
        StringBuilder texto = new StringBuilder();
        texto.append("El tipo de documento ingresado no es valido, coloca alguno de los disponibles: ");
        for(TipoDocumentoEnum tipoDocumento: TipoDocumentoEnum.values()){
            texto.append(tipoDocumento.name()).append("-").append(tipoDocumento.getDescripcion()).append("\n");
        }
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(
                texto.toString()
        ).addConstraintViolation();
        return false;
    }
}
