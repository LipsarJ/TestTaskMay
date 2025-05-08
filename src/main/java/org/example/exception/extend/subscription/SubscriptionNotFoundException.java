package org.example.exception.extend.subscription;

import org.example.exception.NotFoundException;

public class SubscriptionNotFoundException extends NotFoundException {
    public SubscriptionNotFoundException(String message) {
        super(String.format(message));
    }
}
