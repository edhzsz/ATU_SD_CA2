package org.example.collections;

/**
 * Defines the contract for collections that behave as Queues
 * @param <T> Type of the elements that will be stored in this queue
 */
public interface IQueue<T> {
    /**
     * Inserts the specified element into the queue
     * @param element the element argument.
     */
    void enqueue(T element);

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if the queue is emptyG
     */
    T first();

    /**
     * Tests if this Queue is empty.
     * @return {@code true} if and only if this queue contains
     * no items; {@code false} otherwise.
     */
    boolean empty();
}
