package com.project.test.parameta.commons.util.constants;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase que define constantes utilizadas en toda la aplicación.
 */
public class Constantes {

    // Roles
    public static final String ROL_EMPLEADO = "EMPLEADO";

    // Constantes de configuración
    public static final String ERROR_USUARIO_NO_ENCONTRADO = "Error al buscar el usuario: ";
    public static final String ORIGEN_DESARROLLO = "http://localhost:4200";

    // Rutas
    public static final String V3_API_DOCS_PATH = "/v3/api-docs/**";
    public static final String SWAGGER_UI_PATH = "/doc/swagger-ui/***";
    public static final String EMPLEADO_PATH = "/empleado/**";
    public static final String WS_PATH = "/ws/**";

    // Claims y mensajes relacionados con JWT
    public static final String CLAIM_CODIGO_EMPLEADO = "codigoEmpleado";
    public static final String ERROR_IDENTIFICADOR_NO_VALIDO = "El token no contiene un identificador válido.";
    public static final long EXPIRATION_TIME_IN_MILLIS = 24 * 60 * 60 * 1000; // 24 horas

    // Mensajes de validación
    public static final String DEFAULT_MESSAGE_CARGO_VALIDATION = "El cargo ingresado no es válido";
    public static final String DEFAULT_MESSAGE_FECHA_VALIDATION = "La fecha ingresada no es válida";
    public static final String DEFAULT_MESSAGE_TIPO_DOCUMENTO_VALIDATION = "El tipo de documento ingresado no existe";

    // Respuesta HTTP: contenido y estados
    public static final String RESPONSE_CONTENT_TYPE = "application/json";
    public static final int RESPONSE_STATUS_FORBIDDEN = HttpServletResponse.SC_FORBIDDEN;
    public static final String ACCESS_DENIED_MESSAGE = "{\"error\": \"Acceso denegado: No tienes permiso para acceder a este recurso.\"}";

    public static final int RESPONSE_STATUS_UNAUTHORIZED = HttpServletResponse.SC_UNAUTHORIZED;
    public static final String AUTHENTICATION_FAILED_MESSAGE = "Autenticación fallida: ";

    // Claves y mensajes de respuesta
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String PATH_KEY = "path";
    public static final String STATUS_KEY = "status";
    public static final String MESSAGE_KEY = "message";
    public static final String ERRORS_KEY = "errors";
    public static final String VALIDATION_ERRORS_MESSAGE = "Errores de validación";
    public static final String NOT_FOUND_ERROR = "No encontrado";
    public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor";

    // Mensajes de error en logs
    public static final String ERRORES_VALIDACIONES = "Errores de validación: {}";
    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: {}";
    public static final String ERROR_INESPERADO = "Error inesperado: {}";

    // Formato de fecha
    public static final String FORMATO_FECHA = "yyyy-MM-dd";

    // Prefijo y tamaño de tokens Bearer
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int SIZE_BEARER = 7;

    // Límites de códigos
    public static final int MAX_CODIGO = 5;
    public static final int MIN_CODIGO = 0;

    // Caracteres y mensajes relacionados con validaciones
    public static final String LINEA_MITAD = "-";
    public static final String ERROR_CONVERSION_TIPOS = "Error de conversión de tipos: ";
    public static final String CARGO_NO_DISPONIBLE = "El cargo ingresado no es válido, utiliza uno de los disponibles: ";
    public static final String TIPO_DOCUMENTO_NO_DISPONIBLE = "El tipo de documento ingresado no es válido, utiliza uno de los siguientes: ";
    public static final String COMA = ", ";

    // Expresión regular para validación de fechas
    public static final String REGEX_FECHA = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static final String STRING_NO_CUMPLE_FORMATO = "El string no cumple con el formato yyyy-MM-dd.";
}
