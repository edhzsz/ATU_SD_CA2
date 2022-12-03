package org.example.collections.impl;

import org.example.collections.IQueue;
import org.example.collections.NoSuchElementException;

/**
 * A generic queue. Implements the IQueue interface.
 * @param <T> Type of the elements that will be stored in this queue
 */
public class GenericQueue<T> implements IQueue<T> {
    /**
     * Inserts the specified element into the queue
     *
     * @param element the element argument.
     */
    @Override
    public void enqueue(T element) {

    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T dequeue() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is emptyG
     */
    @Override
    public T first() {
        return null;
    }

    /**
     * Tests if this Queue is empty.
     *
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return false;
    }
}
