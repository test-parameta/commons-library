package com.project.test.parameta.commons.util.enums;

public enum TipoDocumentoEnum {

    CC("Cédula de Ciudadanía"),
    CE("Cédula de Extranjería"),
    PAS("Pasaporte");

    private final String descripcion;

    TipoDocumentoEnum(String descripcion){
        this.descripcion = descripcion;

    }
    public String getDescripcion() {
        return descripcion;
    }

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
