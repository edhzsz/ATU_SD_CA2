package org.example.collections.impl;

import org.example.collections.IStack;
import org.example.collections.EmptyStackException;

/**
 * A generic stack. Implements the IStack interface.
 * @param <T> Type of the elements that will be stored in this stack
 */
public class GenericStack<T> implements IStack<T> {
    /**
     * Pushes an item onto the top of this stack
     *
     * @param element the element argument.
     */
    @Override
    public void push(T element) {

    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException if the method is called on an empty stack
     */
    @Override
    public T pop() {
        return null;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException if the method is called on an empty stack
     */
    @Override
    public T peek() {
        return null;
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return false;
    }
}
