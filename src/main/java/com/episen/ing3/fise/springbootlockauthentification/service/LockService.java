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

    public Lock getLockByDocumentId(String documentId){
        Lock lock = lockRepository.findById(documentId).orElse(null);
        return lock;
    }

    public Lock createLock(Lock lock){
        Lock testLock = lockRepository.findById(lock.getDocument_Id()).orElse(null);
        if(testLock!=null)
            return null;
        lock.setCreated(LocalDateTime.now());
        Lock createdLock = lockRepository.insert(lock);
        return createdLock;
    }

    public boolean deleteLock(Lock lockToDelete){
        Lock lock = lockRepository.findById(lockToDelete.getDocument_Id()).orElse(null);
        if(lock==null)
            return false;
        if(lock.getOwner().equals(lockToDelete.getOwner())){
            lockRepository.delete(lock);
            return true;
        }
        return false;

    }


}
