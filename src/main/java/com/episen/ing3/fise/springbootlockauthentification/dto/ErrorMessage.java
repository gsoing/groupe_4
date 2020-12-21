package com.episen.ing3.fise.springbootlockauthentification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private String code;
    private String message;

}
