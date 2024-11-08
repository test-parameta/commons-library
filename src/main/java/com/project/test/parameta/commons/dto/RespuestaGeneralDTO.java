package com.project.test.parameta.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaGeneralDTO implements Serializable {

    private HttpStatus status;

    private Object data;

    private String message;

}
