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

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, HttpClientErrorException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("path", request.getDescription(false));

        if (ex instanceof MethodArgumentNotValidException) {
            // Manejo de validaciones
            log.error("Errores de validación {}", ex.getMessage());
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("message", "Errores de validación");
            body.put("errors", errors);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof HttpClientErrorException) {
            // Manejo de errores 404 o similares
            log.error("Recurso no encontrado: {}", ex.getMessage());
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", "Not Found");
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } else {
            // Manejo de excepciones genéricas
            log.error("Error inesperado: {}", ex.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", "Internal Server Error");
            body.put("message", ex.getMessage()); // Puedes ocultar este mensaje en producción
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
