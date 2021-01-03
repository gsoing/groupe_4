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

/**
 * Généralement on place toute les méthodes sur une ressoirce dans un même controller cela permet d'éviter des effets de bords
 * si on a des pattern d'url qui se croisent
 */

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
        // L'annotation @NotNull aurait fait l'affaire et en plus un attribut @PathVariable est forcément obligatoire
        if(documentId==null)//id is not null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Documents documents = documentService.getDocument(documentId);

        // Ce cas ne peut pas arriver puisque dans la méthode getDocument vous levez une exception si on ne trouve pas le document
        if(documents==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(documents);
    }

    @PutMapping("/{documentId}")
    public  ResponseEntity<Documents> putDocument(@PathVariable("documentId") String documentId, @Valid @RequestBody Documents document, Authentication authentication) {

        // Cette logique devrait être dans le service
        Lock lock = lockService.getLockByDocumentId(documentId);
        if (lock!=null){
            if(!authentication.getName().equals(lock.getOwner()))
                throw new ForbiddenException();
        }

        document.setDocumentId(documentId);
        document.setEditor(authentication.getName());
        Documents updateDocument = documentService.updateDocument(document);

        // Pareil ici ces cas ne peuvent pas se produire, ils sont déjà traité dans le service
        if(updateDocument==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // Et en plus la c'est un peu tard pour s'en occuper
        if(updateDocument.getStatus()== Documents.Status.VALIDATED)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.status(HttpStatus.OK).body(updateDocument);
    }

    //TODO put text/plain
    //DISGUSTING but it works
    // Il est possible de mettre directement le type Document.Status pour status dans la méthode Spring fait la conversion
    // Par contre seul un validateur peut passer le document au statut valider, il manque un bout de la gestion des droits
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
