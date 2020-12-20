package com.episen.ing3.fise.springbootlockauthentification.service;

import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import com.episen.ing3.fise.springbootlockauthentification.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        document.setUpdated(LocalDateTime.now());
        document.setBody(updatedDocument.getBody());
        document.setEditor(updatedDocument.getEditor());
        document.setTitle(updatedDocument.getTitle());
        Documents createdDocuments = documentRepository.save(document);
        return createdDocuments;
    }
    public Documents getDocument(String id){
        Documents document = documentRepository.findById(id).orElseThrow(()  -> null);
        return document;
    }

}
