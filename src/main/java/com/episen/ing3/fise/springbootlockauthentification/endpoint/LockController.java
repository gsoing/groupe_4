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

    public static final String PATH= "/api/v1/document";

    @Autowired
    LockService lockService;
    @Autowired
    DocumentService documentService;

    @GetMapping("/{documentId}/lock")
    public ResponseEntity<Lock> getLock() {


        return ResponseEntity.ok(Lock.builder().build());
    }

    @PutMapping("/{documentId}/lock")
    public ResponseEntity<Lock> putLock(@PathVariable("documentId") String documentId, Authentication authentication) {
        Documents documents = documentService.getDocument(documentId);
        if (documents==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Lock lock = new Lock(documentId,authentication.getName(), null);
        Lock createdLock = lockService.createLock(lock);
        if (createdLock==null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok().body(createdLock);

    }

    @DeleteMapping("/{documentId}/lock")
    public ResponseEntity deleteLock(@RequestBody Documents documents) {
        return ResponseEntity.ok("succesfull delete");

    }
}
