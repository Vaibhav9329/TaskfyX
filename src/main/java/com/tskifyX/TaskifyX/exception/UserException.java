package com.tskifyX.TaskifyX.exception;

import lombok.Getter;

@Getter  // Auto-generates getTitle() and getMessage()
public class UserException extends RuntimeException {
    private final String title;
    private final String message;

    public UserException(String title, String message) {
        this.title = title;
        this.message = message;
    }
}