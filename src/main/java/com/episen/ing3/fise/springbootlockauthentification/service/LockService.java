package com.episen.ing3.fise.springbootlockauthentification.service;

import com.episen.ing3.fise.springbootlockauthentification.model.Lock;
import com.episen.ing3.fise.springbootlockauthentification.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LockService {

    private final LockRepository lockRepository;

    private Lock getLockByDocumentId(String documentId){
        Lock lock = lockRepository.findById(documentId).orElse(null);
        return lock;
    }

    private Lock createLock(Lock lock){
        lock.setCreated(LocalDateTime.now());
        Lock createdLock = lockRepository.insert(lock);
        return createdLock;
    }

    private boolean deleteLock(Lock lockToDelete){
        Lock lock = lockRepository.findById(lockToDelete.getDocument_Id()).orElse(null);
        if(lock==null)
            return false;
        if(lock.getOwner().equals(lockToDelete.getOwner())){
            lockRepository.delete(lock);
            return true;
        }
        return true;
    }


}
