package com.project.test.parameta.commons.util.enums;

/**
 * Enumeración que representa los diferentes tipos de documentos disponibles en la aplicación.
 * <p>
 * Cada elemento de la enumeración tiene un nombre único y una descripción asociada.
 * </p>
 * <ul>
 * <li>CC: Cédula de Ciudadanía</li>
 * <li>CE: Cédula de Extranjería</li>
 * <li>PAS: Pasaporte</li>
 * </ul>
 */
public enum TipoDocumentoEnum {

    CC("Cédula de Ciudadanía"),
    CE("Cédula de Extranjería"),
    PAS("Pasaporte");

    /**
     * Descripción asociada al tipo de documento.
     */
    private final String descripcion;

    /**
     * Constructor para inicializar un tipo de documento con su descripción asociada.
     *
     * @param descripcion la descripción del tipo de documento.
     */
    TipoDocumentoEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción asociada al tipo de documento.
     *
     * @return la descripción del tipo de documento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Verifica si un valor dado corresponde a un nombre o descripción de un tipo de documento válido.
     *
     * @param value el valor a verificar.
     * @return {@code true} si el valor corresponde a un nombre o descripción de un tipo de documento,
     * {@code false} en caso contrario.
     */
    public static boolean existe(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (TipoDocumentoEnum tipoDocumento : TipoDocumentoEnum.values()) {
            if (tipoDocumento.name().equalsIgnoreCase(value) || tipoDocumento.descripcion.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si una descripción dada corresponde a algún tipo de documento.
     *
     * @param descripcion la descripción a verificar.
     * @return {@code true} si la descripción corresponde a un tipo de documento, {@code false} en caso contrario.
     */
    public static boolean esDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            return false;
        }
        for (TipoDocumentoEnum tipoDocumento : TipoDocumentoEnum.values()) {
            if (tipoDocumento.descripcion.equalsIgnoreCase(descripcion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el nombre del tipo de documento a partir de su descripción.
     *
     * @param descripcion la descripción del tipo de documento.
     * @return el nombre del tipo de documento si se encuentra una coincidencia, {@code null} en caso contrario.
     */
    public static String obtenerNombrePorDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            return null;
        }
        for (TipoDocumentoEnum tipoDocumento : TipoDocumentoEnum.values()) {
            if (tipoDocumento.descripcion.equalsIgnoreCase(descripcion)) {
                return tipoDocumento.name();
            }
        }
        return null; // No se encontró ninguna coincidencia
    }
}
