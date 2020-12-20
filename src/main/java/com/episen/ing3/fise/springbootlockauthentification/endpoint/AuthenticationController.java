package com.episen.ing3.fise.springbootlockauthentification.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(AuthenticationController.PATH)
public class AuthenticationController {

    public static final String PATH= "/api/v1/auth";

    @GetMapping
    public ResponseEntity<String> testPublic() {
        return ResponseEntity.ok("Bienvenue sur l'api");
    }
    @PostMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Bienvenue sur l'api");
    }
}
