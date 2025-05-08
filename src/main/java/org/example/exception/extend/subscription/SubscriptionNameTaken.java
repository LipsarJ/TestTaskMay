package org.example.exception.extend.subscription;

import org.example.responses.Errors;
import org.example.exception.BadDataException;

public class SubscriptionNameTaken extends BadDataException {
    public SubscriptionNameTaken(String message, Errors errorCode) {
        super(message, errorCode);
    }
}
