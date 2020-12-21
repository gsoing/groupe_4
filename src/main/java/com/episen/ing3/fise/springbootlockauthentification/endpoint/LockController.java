package com.episen.ing3.fise.springbootlockauthentification.endpoint;

import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.Lock;
import com.episen.ing3.fise.springbootlockauthentification.service.DocumentService;
import com.episen.ing3.fise.springbootlockauthentification.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping(LockController.PATH)
public class LockController {

    public static final String PATH= "/api/v1/documents";

    @Autowired
    LockService lockService;
    @Autowired
    DocumentService documentService;

    @GetMapping("/{documentId}/lock")
    public ResponseEntity<Lock> getLock(@PathVariable("documentId") String documentId) {
        Lock lock = lockService.getLockByDocumentId(documentId);
        if (lock==null)
            return ResponseEntity.status(204).build();
        return ResponseEntity.ok().body(lock);
    }

    @PutMapping("/{documentId}/lock")
    public ResponseEntity<Lock> putLock(@PathVariable("documentId") String documentId, Authentication authentication) {
        documentService.getDocument(documentId);

        Lock lock = new Lock(documentId,authentication.getName(), null);
        Lock createdLock = lockService.createLock(lock);
        return ResponseEntity.status(HttpStatus.LOCKED).body(createdLock);

    }

    @DeleteMapping("/{documentId}/lock")
    public ResponseEntity deleteLock(@PathVariable("documentId") String documentId, Authentication authentication) {
        Lock lock = new Lock(documentId,authentication.getName(),null);
        lockService.deleteLock(lock);
        return ResponseEntity.ok().build();


    }
}
