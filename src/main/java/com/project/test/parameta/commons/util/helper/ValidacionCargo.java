package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.CargoValidartionAnnotation;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Clase que implementa la lógica de validación para la anotación personalizada {@link CargoValidartionAnnotation}.
 * <p>
 * Verifica si el valor proporcionado corresponde a un cargo válido definido en {@link CargoEnum}.
 * Si no es válido, genera un mensaje de error personalizado indicando los valores aceptados.
 * </p>
 */
public class ValidacionCargo implements ConstraintValidator<CargoValidartionAnnotation, String> {

    /**
     * Método que valida si el valor proporcionado es un cargo válido.
     *
     * @param value   el valor del campo que se está validando.
     * @param context el contexto de validación, que se utiliza para construir mensajes de error personalizados.
     * @return {@code true} si el valor es válido, {@code false} de lo contrario.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (CargoEnum.existe(value)) {
            return true;
        }

        // Construir el mensaje de error personalizado con los cargos válidos
        StringBuilder texto = new StringBuilder();
        texto.append(CARGO_NO_DISPONIBLE);
        for (CargoEnum cargo : CargoEnum.values()) {
            texto.append(cargo.name()).append(LINEA_MITAD).append(cargo.getDescripcion()).append(COMA);
        }

        // Deshabilitar el mensaje de error predeterminado
        context.disableDefaultConstraintViolation();

        // Construir un mensaje de error personalizado
        context.buildConstraintViolationWithTemplate(
                texto.toString()
        ).addConstraintViolation();

        return false;
    }
}
