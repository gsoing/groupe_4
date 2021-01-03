package com.episen.ing3.fise.springbootlockauthentification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * C'est pourquoi cette class ?
 */
@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String username;
    private enum role{REDACTEUR,RELECTEUR}
}
