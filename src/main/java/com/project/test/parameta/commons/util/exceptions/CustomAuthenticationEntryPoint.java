package com.project.test.parameta.commons.util.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Clase personalizada que maneja los casos en los que se intenta acceder a un recurso sin autenticación válida.
 * <p>
 * Implementa la interfaz {@link AuthenticationEntryPoint} para personalizar la respuesta cuando ocurre
 * una excepción de autenticación.
 * </p>
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Método que maneja las solicitudes no autenticadas.
     * <p>
     * Este método se invoca cuando un usuario intenta acceder a un recurso sin proporcionar
     * credenciales válidas. Devuelve una respuesta HTTP 401 (No autorizado) con un mensaje de error en formato JSON.
     * </p>
     *
     * @param request       el objeto {@link HttpServletRequest} que representa la solicitud.
     * @param response      el objeto {@link HttpServletResponse} que representa la respuesta.
     * @param authException la excepción que contiene detalles de la falla de autenticación.
     * @throws IOException      si ocurre un error de E/S al escribir la respuesta.
     * @throws ServletException si ocurre un error relacionado con el manejo de la solicitud.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Devolver un código 401 con un mensaje personalizado
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setStatus(RESPONSE_STATUS_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"" + AUTHENTICATION_FAILED_MESSAGE + authException.getMessage() + "\"}");
    }
}
