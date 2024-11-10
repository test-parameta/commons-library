package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.util.anotations.TipoDocumentoValidationAnnotation;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Clase que implementa la lógica de validación para la anotación personalizada {@link TipoDocumentoValidationAnnotation}.
 * <p>
 * Verifica si un valor de tipo {@code String} corresponde a un tipo de documento válido definido en {@link TipoDocumentoEnum}.
 * Si el valor no es válido, genera un mensaje de error personalizado indicando los tipos de documento disponibles.
 * </p>
 */
public class ValidacionTipoDocumento implements ConstraintValidator<TipoDocumentoValidationAnnotation, String> {

    /**
     * Método que valida si el valor proporcionado es un tipo de documento válido.
     *
     * @param value   el valor del campo que se está validando.
     * @param context el contexto de validación, que se utiliza para construir mensajes de error personalizados.
     * @return {@code true} si el valor corresponde a un tipo de documento válido, {@code false} de lo contrario.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Verifica si el valor corresponde a un tipo de documento válido
        if (TipoDocumentoEnum.existe(value)) {
            return true;
        }

        // Construye un mensaje de error personalizado con los tipos de documento disponibles
        StringBuilder texto = new StringBuilder();
        texto.append(TIPO_DOCUMENTO_NO_DISPONIBLE);
        for (TipoDocumentoEnum tipoDocumento : TipoDocumentoEnum.values()) {
            texto.append(tipoDocumento.name()).append(LINEA_MITAD).append(tipoDocumento.getDescripcion()).append(COMA);
        }

        // Deshabilita el mensaje de error predeterminado
        context.disableDefaultConstraintViolation();

        // Agrega el mensaje de error personalizado
        context.buildConstraintViolationWithTemplate(
                texto.toString()
        ).addConstraintViolation();

        return false;
    }
}
