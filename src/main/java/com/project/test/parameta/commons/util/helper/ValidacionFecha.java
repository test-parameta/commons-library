package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.FechaValidationAnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionFecha implements ConstraintValidator<FechaValidationAnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // Llama a la utilidad para validar el formato de la fecha
            Utilidades.validarFormatoFecha(value);
            return true;
        } catch (IllegalArgumentException e) {
            // Deshabilita el mensaje por defecto
            context.disableDefaultConstraintViolation();

            // Usa el mensaje de la excepción como parte del mensaje dinámico
            context.buildConstraintViolationWithTemplate(
                    "Error en la fecha ingresada: " + e.getMessage()
            ).addConstraintViolation();

            return false;
        } catch (Exception e) {
            // Captura otras excepciones, si las hay
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "La fecha ingresada no es válida debido a un error inesperado."
            ).addConstraintViolation();
            return false;
        }
    }
}
