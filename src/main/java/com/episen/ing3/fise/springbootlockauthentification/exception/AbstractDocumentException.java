package com.episen.ing3.fise.springbootlockauthentification.exception;

import com.episen.ing3.fise.springbootlockauthentification.dto.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.swing.text.AbstractDocument;

@Getter
public class AbstractDocumentException extends RuntimeException {

    private final transient ErrorMessage errorMessage;
    private final HttpStatus httpStatus;



    public AbstractDocumentException(HttpStatus httpStatus, ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public AbstractDocumentException(ErrorMessage errorMessage) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }
}


