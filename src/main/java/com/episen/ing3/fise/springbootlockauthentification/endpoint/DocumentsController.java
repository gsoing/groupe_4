package com.episen.ing3.fise.springbootlockauthentification.endpoint;


import com.episen.ing3.fise.springbootlockauthentification.model.DocumentList;
import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(DocumentsController.PATH)
public class DocumentsController {

    public static final String PATH= "/api/v1/documents";
    @Autowired
    DocumentService documentService;

    @GetMapping
    public ResponseEntity<Page<Documents>> getAllDocuments(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Documents> documentsList = documentService.getAllDocuments(pageable);

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
