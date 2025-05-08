package org.example.exception.extend.user;

import org.example.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(String.format(message));
    }
}
