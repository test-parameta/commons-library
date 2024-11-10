package com.project.test.parameta.commons.util.anotations;

import com.project.test.parameta.commons.util.helper.ValidacionFecha;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.project.test.parameta.commons.util.constants.Constantes.DEFAULT_MESSAGE_FECHA_VALIDATION;

/**
 * Anotación personalizada para validar el formato o la validez de una fecha en un campo o parámetro.
 * <p>
 * Esta anotación utiliza la clase {@link ValidacionFecha} para realizar la lógica de validación.
 * </p>
 *
 * <p>
 * Se puede aplicar a campos o parámetros de métodos.
 * </p>
 */
@Constraint(validatedBy = ValidacionFecha.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaValidationAnotation {

    /**
     * Mensaje de error por defecto que se muestra si la validación falla.
     *
     * @return el mensaje de error.
     */
    String message() default DEFAULT_MESSAGE_FECHA_VALIDATION;

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
