package com.project.test.parameta.commons.util.enums;

public enum CargoEnum {

    GG("Gerente General"),
    DT("Director de Tecnología"),
    AS("Analista de Sistemas"),
    IS("Ingeniero de Software"),
    ABD("Administrador de Base de Datos"),
    DF("Desarrollador Frontend"),
    DB("Desarrollador Backend"),
    ESI("Especialista en Seguridad Informática"),
    SM("Scrum Master"),
    PO("Product Owner");

    private final String descripcion;

    CargoEnum(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static boolean existe(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (CargoEnum cargo : CargoEnum.values()) {
            if (cargo.name().equalsIgnoreCase(value) || cargo.descripcion.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean esDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            return false;
        }
        for (CargoEnum cargo : CargoEnum.values()) {
            if (cargo.descripcion.equalsIgnoreCase(descripcion)) {
                return true;
            }
        }
        return false;
    }

    public static String obtenerNombrePorDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            return null;
        }
        for (CargoEnum cargo : CargoEnum.values()) {
            if (cargo.descripcion.equalsIgnoreCase(descripcion)) {
                return cargo.name();
            }
        }
        return null; // No se encontró ninguna coincidencia
    }

}
