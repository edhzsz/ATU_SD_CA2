package org.example.collections.impl;

import org.example.collections.IList;
import org.example.collections.IQueue;
import org.example.collections.NoSuchElementException;

/**
 * A generic queue. Implements the IQueue interface.
 * @param <T> Type of the elements that will be stored in this queue
 */
public class GenericQueue<T> implements IQueue<T> {
    private IList<T> backingList;

    public GenericQueue() {
        this(new GenericArrayList<T>());
    }

    public GenericQueue(IList<T> backingList) {
        this.backingList = backingList;
    }

    /**
     * Inserts the specified element into the queue
     *
     * @param element the element argument.
     */
    @Override
    public void enqueue(T element) {
        backingList.add(element);
    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T dequeue() {
        if(backingList.isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        return backingList.remove(0);
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is emptyG
     */
    @Override
    public T first() {
        if(backingList.isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        return backingList.get(0);
    }

    /**
     * Tests if this Queue is empty.
     *
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    @Override
    public boolean empty() {
        return backingList.isEmpty();
    }
}
