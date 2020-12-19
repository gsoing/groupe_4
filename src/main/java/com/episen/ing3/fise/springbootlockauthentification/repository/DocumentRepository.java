package com.episen.ing3.fise.springbootlockauthentification.repository;

import com.episen.ing3.fise.springbootlockauthentification.model.Documents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Documents,String> {

    Page<Documents> findByDocumentId (String nickname, Pageable pageable);

}
