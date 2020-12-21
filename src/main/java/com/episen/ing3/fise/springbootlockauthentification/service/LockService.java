package com.episen.ing3.fise.springbootlockauthentification.service;

import com.episen.ing3.fise.springbootlockauthentification.exception.ConflictException;
import com.episen.ing3.fise.springbootlockauthentification.exception.ForbiddenException;
import com.episen.ing3.fise.springbootlockauthentification.exception.NotFoundException;
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
        lockRepository.findById(lock.getDocument_Id()).orElseThrow(ConflictException::new);
        lock.setCreated(LocalDateTime.now());
        Lock createdLock = lockRepository.insert(lock);
        return createdLock;
    }

    public boolean deleteLock(Lock lockToDelete){
        Lock lock = lockRepository.findById(lockToDelete.getDocument_Id()).orElseThrow(NotFoundException::new);

        if(!lock.getOwner().equals(lockToDelete.getOwner()))
            throw new ForbiddenException();

        lockRepository.delete(lock);
        return true;

    }


}
