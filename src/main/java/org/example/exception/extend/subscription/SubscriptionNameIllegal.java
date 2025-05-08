package org.example.exception.extend.subscription;

import org.example.responses.Errors;
import org.example.exception.BadDataException;

public class SubscriptionNameIllegal extends BadDataException {
    public SubscriptionNameIllegal(String message, Errors errorCode) {
        super(message, errorCode);
    }
}
