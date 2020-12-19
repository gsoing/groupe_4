package com.episen.ing3.fise.springbootlockauthentification.endpoint.documents;

import com.episen.ing3.fise.springbootlockauthentification.endpoint.authentication.AuthenticationController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DocumentsController.PATH)
public class DocumentsController {

    public static final String PATH= "/api/v1/documents";

    @GetMapping
    public ResponseEntity<String> testPublic(Authentication authentication) {
        return ResponseEntity.ok("Bienvenue jeune rédacteur");
    }
}
