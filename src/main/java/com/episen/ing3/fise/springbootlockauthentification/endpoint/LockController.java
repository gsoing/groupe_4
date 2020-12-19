package com.episen.ing3.fise.springbootlockauthentification.endpoint;

import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.model.Lock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(LockController.PATH)
public class LockController {

    public static final String PATH= "/api/v1/document";

    @GetMapping("/{documentId}/lock")
    public ResponseEntity<Lock> getLock(Authentication authentication) {

        return ResponseEntity.ok(Lock.builder().build());
    }

    @PutMapping("/{documentId}/lock")
    public ResponseEntity<Lock> putLock(@RequestBody Documents documents, Authentication authentication, Lock lock) {
        return ResponseEntity.ok(Lock.builder().build());

    }

    @DeleteMapping("/{documentId}/lock")
    public ResponseEntity deleteLock(@RequestBody Documents documents, Authentication authentication) {
        return ResponseEntity.ok("succesfull delete");

    }
}
