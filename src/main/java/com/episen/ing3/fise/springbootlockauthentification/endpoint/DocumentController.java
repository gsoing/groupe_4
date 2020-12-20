package com.episen.ing3.fise.springbootlockauthentification.endpoint;



import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping(DocumentController.PATH)
public class DocumentController {

    public static final String PATH= "/api/v1/documents";

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
    public  ResponseEntity<Documents> putDocument(@PathVariable("documentId") String documentId, @Valid @RequestBody Documents document) {
        document.setDocumentId(documentId);
        Documents updateDocument = documentService.updateDocument(document);
        if(updateDocument==null)
            return (ResponseEntity<Documents>) ResponseEntity.notFound();
        return ResponseEntity.status(HttpStatus.OK).body(updateDocument);
    }
    //TODO put text/plain
    //DISGUSTING but it works
    @PutMapping(value = "/{documentId}/status")
    public  ResponseEntity putStatus(@PathVariable("documentId") String documentId, @RequestBody String status) {
        Documents updateDocument;
        if (status.contains("VALIDATED")){
            updateDocument = documentService.updateStatus(documentId, Documents.Status.VALIDATED);
        }else if(status.contains("CREATED")){
            updateDocument = documentService.updateStatus(documentId, Documents.Status.CREATED);
        }else{
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        if(updateDocument==null)
            return (ResponseEntity) ResponseEntity.notFound();
        return ResponseEntity.ok().build();
    }



}
