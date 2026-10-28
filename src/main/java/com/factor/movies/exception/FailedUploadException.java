package com.factor.movies.exception;

public class FailedUploadException extends RuntimeException {
    public FailedUploadException(String message) {
        super(message);
    }
}
