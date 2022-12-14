package org.example.collections;

/**
 * Defines the contract for collections that behave as Stacks
 * @param <T> Type of the elements that will be stored in this stack
 */
public interface IStack<T> {
    /**
     * Pushes an item onto the top of this stack
     * @param element the element argument.
     */
    void push(T element);

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException if the method is called on an empty stack
     */
    T pop();

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException if the method is called on an empty stack
     */
    T peek();

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
     boolean empty();
}
