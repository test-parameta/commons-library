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

public class Utilidades {

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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    yield Optional.of(clazz.cast(dateFormat.parse(obj.toString())));
                }
                case "BigDecimal" -> Optional.of(clazz.cast(BigDecimal.valueOf(Double.parseDouble(obj.toString()))));
                default -> Optional.empty();
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Error de conversión de tipos: " + e.getMessage(), e);
        }
    }

    public static <T> JAXBElement<T> createJaxbElement(T object, Class<T> clazz) {
        return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
    }

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

    public  static Date gregorianToDate(XMLGregorianCalendar fecha){
        if (fecha == null) {
            return null;
        }
        return fecha.toGregorianCalendar().getTime();
    }

    public static String generarCodigo(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
    }

    public static String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static <T> T convertJsonToDto(File jsonFile, Class<T> dtoClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonFile, dtoClass);
    }
}
