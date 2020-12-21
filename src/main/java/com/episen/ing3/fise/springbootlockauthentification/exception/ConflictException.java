package com.episen.ing3.fise.springbootlockauthentification.exception;

import com.episen.ing3.fise.springbootlockauthentification.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

public class ConflictException extends AbstractDocumentException {
    public static final String CONFLICT_CODE = "err.func.wired.conflict";
    public static final String CONFLICT_MESSAGE =
            "The request could not be completed, the document is already locked by someone else";

    public static final ConflictException DEFAULT = new ConflictException(CONFLICT_MESSAGE);

    public ConflictException(String message) {
        this(CONFLICT_CODE, message);
    }

    public ConflictException(String code, String message) {
        super(HttpStatus.CONFLICT,
                ErrorMessage.builder()
                        .code(code)
                        .message(message)
                        .build());
    }
}
