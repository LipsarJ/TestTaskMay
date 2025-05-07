package org.example.exception.extend;

import org.example.controlleradvice.Errors;
import org.example.exception.BadDataException;

public class SubscriptionNameTaken extends BadDataException {
    public SubscriptionNameTaken(String message, Errors errorCode) {
        super(message, errorCode);
    }
}
