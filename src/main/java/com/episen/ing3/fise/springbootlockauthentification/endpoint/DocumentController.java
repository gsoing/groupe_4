package com.episen.ing3.fise.springbootlockauthentification.endpoint;



import com.episen.ing3.fise.springbootlockauthentification.exception.ForbiddenException;
import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.Lock;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import com.episen.ing3.fise.springbootlockauthentification.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(DocumentController.PATH)
public class DocumentController {

    public static final String PATH= "/api/v1/documents";

    @Autowired
    DocumentService documentService;

    @Autowired
    LockService lockService;

    @GetMapping("/{documentId}")
    public  ResponseEntity<Documents> getDocument(@PathVariable("documentId") String documentId) {
        if(documentId==null)//id is not null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Documents documents = documentService.getDocument(documentId);
        if(documents==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(documents);
    }

    @PutMapping("/{documentId}")
    public  ResponseEntity<Documents> putDocument(@PathVariable("documentId") String documentId, @Valid @RequestBody Documents document, Authentication authentication) {
        Lock lock = lockService.getLockByDocumentId(documentId);
        if (lock!=null){
            if(!authentication.getName().equals(lock.getOwner()))
                throw new ForbiddenException();
        }

        document.setDocumentId(documentId);
        document.setEditor(authentication.getName());
        Documents updateDocument = documentService.updateDocument(document);
        if(updateDocument==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(updateDocument.getStatus()== Documents.Status.VALIDATED)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(updateDocument==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok().build();
    }



}
