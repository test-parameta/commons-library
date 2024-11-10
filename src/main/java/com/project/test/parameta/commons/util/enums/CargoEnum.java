package com.project.test.parameta.commons.util.enums;

/**
 * Enumeración que representa los diferentes cargos disponibles en la aplicación.
 * <p>
 * Cada elemento de la enumeración tiene un nombre único y una descripción asociada.
 * </p>
 * <ul>
 * <li>GG: Gerente General</li>
 * <li>DT: Director de Tecnología</li>
 * <li>AS: Analista de Sistemas</li>
 * <li>IS: Ingeniero de Software</li>
 * <li>ABD: Administrador de Base de Datos</li>
 * <li>DF: Desarrollador Frontend</li>
 * <li>DB: Desarrollador Backend</li>
 * <li>ESI: Especialista en Seguridad Informática</li>
 * <li>SM: Scrum Master</li>
 * <li>PO: Product Owner</li>
 * </ul>
 */
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

    /**
     * Descripción asociada al cargo.
     */
    private final String descripcion;

    /**
     * Constructor para inicializar un cargo con su descripción asociada.
     *
     * @param descripcion la descripción del cargo.
     */
    CargoEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción asociada al cargo.
     *
     * @return la descripción del cargo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Verifica si un valor dado corresponde a un nombre o descripción de un cargo válido.
     *
     * @param value el valor a verificar.
     * @return {@code true} si el valor corresponde a un nombre o descripción de un cargo, {@code false} en caso contrario.
     */
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

    /**
     * Verifica si una descripción dada corresponde a algún cargo.
     *
     * @param descripcion la descripción a verificar.
     * @return {@code true} si la descripción corresponde a un cargo, {@code false} en caso contrario.
     */
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

    /**
     * Obtiene el nombre del cargo a partir de su descripción.
     *
     * @param descripcion la descripción del cargo.
     * @return el nombre del cargo si se encuentra una coincidencia, {@code null} en caso contrario.
     */
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
