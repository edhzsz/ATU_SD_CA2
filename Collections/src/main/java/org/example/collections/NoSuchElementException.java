package org.example.collections;

/**
 * Exception thrown when trying to execute operations on empty queues
 */
public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
