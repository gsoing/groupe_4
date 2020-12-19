package com.episen.ing3.fise.springbootlockauthentification.endpoint;


import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.DocumentsList;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(DocumentsController.PATH)
public class DocumentsController {

    public static final String PATH= "/api/v1/documents";

    @GetMapping
    public ResponseEntity<DocumentsList> getAllDocuments(Authentication authentication) {
        return ResponseEntity.ok(DocumentsList.builder()
                .nbElements(12)
                .page(12)
                .build());
    }

    @PostMapping
    public ResponseEntity<DocumentsList> createDocument(@RequestBody Documents document, Authentication authentication) {
        return ResponseEntity.ok(DocumentsList.builder()
                .nbElements(12)
                .page(12) //TODO add document
                .build());
    }

}
