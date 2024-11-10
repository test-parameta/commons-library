package com.project.test.parameta.commons.util.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Clase global para el manejo centralizado de excepciones en toda la aplicación.
 * <p>
 * Proporciona controladores de excepciones personalizados para manejar diferentes tipos de errores,
 * como validaciones fallidas, recursos no encontrados y errores internos del servidor.
 * </p>
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * Maneja todas las excepciones que ocurren en la aplicación, incluidas las excepciones genéricas,
     * errores de cliente HTTP y excepciones de validación de argumentos.
     *
     * @param ex      la excepción lanzada.
     * @param request el objeto {@link WebRequest} que contiene detalles de la solicitud.
     * @return un {@link ResponseEntity} con un cuerpo de respuesta que describe el error.
     */
    @ExceptionHandler({Exception.class, HttpClientErrorException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_KEY, LocalDateTime.now());
        body.put(PATH_KEY, request.getDescription(false));

        if (ex instanceof MethodArgumentNotValidException) {
            // Manejo de validaciones fallidas
            log.error(ERRORES_VALIDACIONES, ex.getMessage());
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            body.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            body.put(MESSAGE_KEY, VALIDATION_ERRORS_MESSAGE);
            body.put(ERRORS_KEY, errors);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof HttpClientErrorException) {
            // Manejo de errores 404 u otros errores de cliente
            log.error(RECURSO_NO_ENCONTRADO, ex.getMessage());
            body.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
            body.put(MESSAGE_KEY, NOT_FOUND_ERROR);
            body.put(ERRORS_KEY, ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } else {
            // Manejo de excepciones genéricas
            log.error(ERROR_INESPERADO, ex.getMessage());
            body.put(STATUS_KEY, HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put(MESSAGE_KEY, INTERNAL_SERVER_ERROR);
            body.put(ERRORS_KEY, ex.getMessage()); // Puedes ocultar este mensaje en producción
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
