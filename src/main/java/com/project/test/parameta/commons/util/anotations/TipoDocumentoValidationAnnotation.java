package com.project.test.parameta.commons.util.anotations;

import com.project.test.parameta.commons.util.helper.ValidacionFecha;
import com.project.test.parameta.commons.util.helper.ValidacionTipoDocumento;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidacionTipoDocumento.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoDocumentoValidationAnnotation {

    String message() default "El tipo de documento ingresado no existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
