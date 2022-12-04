package org.example.collections;

/**
 * Exception thrown when attempting to pop or peak on empty stacks
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }
}
