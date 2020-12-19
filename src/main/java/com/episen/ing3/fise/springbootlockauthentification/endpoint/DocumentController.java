package com.episen.ing3.fise.springbootlockauthentification.endpoint;



import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DocumentsController.PATH)
public class DocumentController {

    public static final String PATH= "/api/v1/documents";

    @GetMapping("/{documentId}")
    public void getDocuments(Authentication authentication) {

    }

    @PutMapping("/{documentId}")
    public void putDocuments(@RequestBody Documents document, Authentication authentication) {
    }

}
