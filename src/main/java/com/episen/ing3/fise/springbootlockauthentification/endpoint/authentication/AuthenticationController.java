package com.episen.ing3.fise.springbootlockauthentification.endpoint.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthenticationController.PATH)
public class AuthenticationController {

    public static final String PATH= "/api/v1/auth";

    @GetMapping
    public ResponseEntity<String> testPublic(Authentication authentication) {
        return ResponseEntity.ok("Bienvenue sur l'api");
    }
}
