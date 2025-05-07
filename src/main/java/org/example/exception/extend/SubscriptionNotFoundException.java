package org.example.exception.extend;

import org.example.exception.NotFoundException;

public class SubscriptionNotFoundException extends NotFoundException {
    public SubscriptionNotFoundException(String message) {
        super(String.format(message));
    }
}
