package com.episen.ing3.fise.springbootlockauthentification.service;

import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Documents createDocument(Documents document){
        log.debug("document {}", document.toString());
        document.setCreated(LocalDateTime.now());
        document.setStatus(Documents.Status.CREATED);
        document.setEditor(null);
        Documents createdDocuments = documentRepository.insert(document);
        return createdDocuments;
    }

    public Documents updateDocument(Documents updatedDocument){
        Documents document = documentRepository.findById(updatedDocument.getDocumentId()).orElseThrow(()  -> null);
        if(document==null){
            return null;
        }
        if(document.getStatus()== Documents.Status.VALIDATED)
            return document;
        document.setUpdated(LocalDateTime.now());
        document.setBody(updatedDocument.getBody());
        document.setEditor(updatedDocument.getEditor());//TODO get editor
        document.setTitle(updatedDocument.getTitle());
        Documents createdDocuments = documentRepository.save(document);
        return createdDocuments;
    }
    public Documents getDocument(String id){
        Documents document = documentRepository.findById(id).orElseThrow(()  -> null);
        return document;
    }

    public Documents updateStatus(String documentId, Documents.Status status) {
        Documents document = documentRepository.findById(documentId).orElseThrow(()  -> null);
        if(document==null) {
            return null;
        }
        document.setStatus(status);
        return documentRepository.save(document);
    }

    public List<Documents> getAllDocuments() {
        return documentRepository.findAll();
    }
}
