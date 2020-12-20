package com.episen.ing3.fise.springbootlockauthentification.endpoint;



import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping(DocumentController.PATH)
public class DocumentController {

    public static final String PATH= "/api/v1/document";

    @Autowired
    DocumentService documentService;

    @GetMapping("/{documentId}")
    public  ResponseEntity<Documents> getDocument(@PathVariable("documentId") String documentId) {
        if(documentId==null)
            return (ResponseEntity<Documents>) ResponseEntity.badRequest();
        Documents documents = documentService.getDocument(documentId);
        if(documents==null)
            return (ResponseEntity<Documents>) ResponseEntity.notFound();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(documents);
    }

    @PutMapping("/{documentId}")
    public  ResponseEntity<Documents> putDocument(@PathVariable("documentsId") String documentId, @Valid @RequestBody Documents document) {

        return ResponseEntity.ok(Documents.builder().build());
    }

}
