package com.project.test.parameta.commons.util.anotations;

import com.project.test.parameta.commons.util.helper.ValidacionCargo;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.project.test.parameta.commons.util.constants.Constantes.DEFAULT_MESSAGE_CARGO_VALIDATION;

/**
 * Anotación personalizada para validar el campo de cargo en una entidad o DTO.
 * <p>
 * Esta anotación utiliza la clase {@link ValidacionCargo} para realizar la validación del valor del cargo.
 * </p>
 *
 * <p>
 * Se puede aplicar a campos o parámetros de métodos.
 * </p>
 */
@Constraint(validatedBy = ValidacionCargo.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CargoValidartionAnnotation {

    /**
     * Mensaje de error por defecto que se muestra si la validación falla.
     *
     * @return el mensaje de error.
     */
    String message() default DEFAULT_MESSAGE_CARGO_VALIDATION;

    /**
     * Grupos de validación asociados con esta anotación.
     *
     * @return un arreglo de clases para los grupos de validación.
     */
    Class<?>[] groups() default {};

    /**
     * Carga útil personalizada para esta restricción de validación.
     *
     * @return un arreglo de clases que extienden {@link Payload}.
     */
    Class<? extends Payload>[] payload() default {};
}
