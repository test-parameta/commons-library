package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.FechaValidationAnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.project.test.parameta.commons.util.constants.Constantes.REGEX_FECHA;
import static com.project.test.parameta.commons.util.constants.Constantes.STRING_NO_CUMPLE_FORMATO;

/**
 * Clase que implementa la lógica de validación para la anotación personalizada {@link FechaValidationAnotation}.
 * <p>
 * Verifica si un valor de tipo {@code String} cumple con el formato de fecha `yyyy-MM-dd`.
 * Si el formato no es válido, genera un mensaje de error personalizado.
 * </p>
 */
public class ValidacionFecha implements ConstraintValidator<FechaValidationAnotation, String> {

    /**
     * Método que valida si el valor proporcionado cumple con el formato de fecha `yyyy-MM-dd`.
     *
     * @param value   el valor del campo que se está validando.
     * @param context el contexto de validación, que se utiliza para construir mensajes de error personalizados.
     * @return {@code true} si el valor cumple con el formato, {@code false} de lo contrario.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            // Expresión regular para validar el formato de la fecha
            String regex = REGEX_FECHA;

            // Compila la expresión regular
            Pattern pattern = Pattern.compile(regex);

            // Verifica si el valor no coincide con el patrón
            if (!pattern.matcher(value).matches()) {
                // Deshabilita el mensaje de error predeterminado
                context.disableDefaultConstraintViolation();

                // Construye un mensaje de error personalizado
                context.buildConstraintViolationWithTemplate(
                        STRING_NO_CUMPLE_FORMATO
                ).addConstraintViolation();

                return false;
            }
        }
        return true;
    }
}
