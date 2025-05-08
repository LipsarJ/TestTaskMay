package org.example.exception.extend.user;

import org.example.controlleradvice.Errors;
import org.example.exception.BadDataException;

public class UsernameTakenException extends BadDataException {
    public UsernameTakenException(String message, Errors errorCode) {
        super(message, errorCode);
    }
}
