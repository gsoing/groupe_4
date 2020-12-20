package com.episen.ing3.fise.springbootlockauthentification.endpoint;


import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.DocumentsList;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(DocumentsController.PATH)
public class DocumentsController {

    public static final String PATH= "/api/v1/documents";
    @Autowired
    DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documentsList = documentService.getAllDocuments();
        return ResponseEntity.status(HttpStatus.OK).body(documentsList);
    }

    @PostMapping
    public ResponseEntity<Documents> createDocument(@RequestBody Documents document, Authentication authentication) {
        document.setCreator(authentication.getName());
        Documents documents = documentService.createDocument(document);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documents);
    }

}
