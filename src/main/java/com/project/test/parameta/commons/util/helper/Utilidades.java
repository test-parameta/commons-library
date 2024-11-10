package com.project.test.parameta.commons.util.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBElement;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Clase de utilidad que proporciona métodos para la conversión, validación y manipulación de datos comunes en la aplicación.
 */
public class Utilidades {

    /**
     * Verifica y convierte un objeto al tipo especificado si es posible.
     *
     * @param obj   el objeto a verificar y convertir.
     * @param clazz la clase del tipo objetivo.
     * @param <T>   el tipo objetivo.
     * @return un {@link Optional} con el objeto convertido, o vacío si no es convertible.
     * @throws IllegalArgumentException si ocurre un error de conversión.
     */
    public static <T> Optional<T> checkType(Object obj, Class<T> clazz) {
        if (Objects.isNull(obj)) {
            return Optional.empty();
        }

        try {
            return switch (clazz.getSimpleName()) {
                case "Long" -> Optional.of(clazz.cast(Long.valueOf(obj.toString())));
                case "String" -> Optional.of(clazz.cast(String.valueOf(obj)));
                case "Integer" -> Optional.of(clazz.cast(Integer.valueOf(obj.toString())));
                case "Double" -> Optional.of(clazz.cast(Double.valueOf(obj.toString())));
                case "Date" -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
                    dateFormat.setLenient(false);
                    yield Optional.of(clazz.cast(dateFormat.parse(obj.toString())));
                }
                case "BigDecimal" -> Optional.of(clazz.cast(BigDecimal.valueOf(Double.parseDouble(obj.toString()))));
                default -> Optional.empty();
            };
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_CONVERSION_TIPOS + e.getMessage(), e);
        }
    }

    /**
     * Crea un elemento JAXB a partir de un objeto.
     *
     * @param object el objeto que se desea envolver.
     * @param clazz  la clase del objeto.
     * @param <T>    el tipo del objeto.
     * @return un {@link JAXBElement} que envuelve el objeto.
     */
    public static <T> JAXBElement<T> createJaxbElement(T object, Class<T> clazz) {
        return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
    }

    /**
     * Convierte un objeto {@link Date} a un {@link XMLGregorianCalendar}.
     *
     * @param date la fecha a convertir.
     * @return el objeto {@link XMLGregorianCalendar} equivalente, o null si la conversión falla.
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        try {
            if (date == null) {
                return null; // Maneja el caso en el que la fecha sea nula
            }

            // Convierte Date a GregorianCalendar
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            // Convierte GregorianCalendar a XMLGregorianCalendar
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja excepciones devolviendo null
        }
    }

    /**
     * Convierte un objeto {@link XMLGregorianCalendar} a un {@link Date}.
     *
     * @param fecha el objeto {@link XMLGregorianCalendar} a convertir.
     * @return el objeto {@link Date} equivalente, o null si la entrada es null.
     */
    public static Date gregorianToDate(XMLGregorianCalendar fecha) {
        if (fecha == null) {
            return null;
        }
        return fecha.toGregorianCalendar().getTime();
    }

    /**
     * Genera un código único de 5 caracteres en mayúsculas.
     *
     * @return un código único de 5 caracteres.
     */
    public static String generarCodigo() {
        return UUID.randomUUID().toString().replace(LINEA_MITAD, "").substring(MIN_CODIGO, MAX_CODIGO).toUpperCase();
    }

    /**
     * Convierte un objeto {@link Date} a una cadena con formato "yyyy-MM-dd".
     *
     * @param date la fecha a convertir.
     * @return la representación en cadena de la fecha.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA);
        return formatter.format(date);
    }

    /**
     * Convierte un archivo JSON en un objeto DTO.
     *
     * @param jsonFile el archivo JSON a convertir.
     * @param dtoClass la clase del DTO objetivo.
     * @param <T>      el tipo del DTO.
     * @return el objeto DTO convertido.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public static <T> T convertJsonToDto(File jsonFile, Class<T> dtoClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonFile, dtoClass);
    }
}
