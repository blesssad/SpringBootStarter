package com.openschool.bookapplication.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookException extends RuntimeException{
    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookException(Throwable cause) {
        super(cause);
    }
}
