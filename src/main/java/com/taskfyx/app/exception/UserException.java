package com.taskfyx.app.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final String title;

    public UserException(String title, String message) {
        super(message);
        this.title = title;
    }

}
