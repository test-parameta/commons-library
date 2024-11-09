package com.project.test.parameta.commons.util.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static String generarCodigo(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
    }

    public static <T> T convertJsonToDto(File jsonFile, Class<T> dtoClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonFile, dtoClass);
    }
}
