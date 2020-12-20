package com.episen.ing3.fise.springbootlockauthentification.endpoint;


import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.DocumentsList;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(DocumentsController.PATH)
public class DocumentsController {

    public static final String PATH= "/api/v1/documents";
    @Autowired
    DocumentService documentService;

    @GetMapping
    public ResponseEntity<DocumentsList> getAllDocuments() {
        return ResponseEntity.ok(DocumentsList.builder()
                .nbElements(12)
                .page(12)
                .build());
    }

    @PostMapping
    public ResponseEntity<Documents> createDocument(@RequestBody Documents document) {
        Documents documents = documentService.createDocument(document);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documents);
    }

}
