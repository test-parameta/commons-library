package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.FechaValidationAnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidacionFecha implements ConstraintValidator<FechaValidationAnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        // Llama a la utilidad para validar el formato de la fecha
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(value).matches()) {
            // Deshabilita el mensaje por defecto
            context.disableDefaultConstraintViolation();

            // Usa el mensaje de la excepción como parte del mensaje dinámico
            context.buildConstraintViolationWithTemplate(
                    "El string no cumple con el formato yyyy-MM-dd."
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
