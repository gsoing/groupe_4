package com.episen.ing3.fise.springbootlockauthentification.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Techniquement il y en a plus que cela, déjà toutes les exceptions de spring
 */
@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({NotFoundException.class,
            BadRequestException.class,
            ForbiddenException.class,
            ConflictException.class})
    public final ResponseEntity<Object> handleNotFoundException(AbstractDocumentException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorMessage());
    }
}
