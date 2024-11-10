package com.project.test.parameta.commons.util.anotations;

import com.project.test.parameta.commons.util.helper.ValidacionCargo;
import com.project.test.parameta.commons.util.helper.ValidacionFecha;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidacionCargo.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CargoValidartionAnnotation {

    String message() default "El cargo ingresado no es valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
