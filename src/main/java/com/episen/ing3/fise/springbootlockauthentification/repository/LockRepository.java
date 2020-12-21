package com.episen.ing3.fise.springbootlockauthentification.repository;

import com.episen.ing3.fise.springbootlockauthentification.model.Lock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LockRepository extends MongoRepository<Lock,String> {}
