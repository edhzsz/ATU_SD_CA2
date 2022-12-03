package org.example.collections.impl;

import org.example.collections.IList;

/**
 * A generic list backed by an array. Implements the IList interface.
 * @param <T> Type of the elements that will be stored in this list.
 */
public class GenericArrayList<T> implements IList<T> {
    /**
     * Append to the end of the list
     *
     * @param elem element that will be appended to the end of the list
     */
    @Override
    public void add(T elem) {

    }

    /**
     * Gets the element at the given index.
     *
     * @param index the position in the list
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of the list bounds
     */
    @Override
    public T get(int index) {
        return null;
    }

    /**
     * Returns the quantity of elements in the list
     *
     * @return the quantity of elements in the list
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes an element at the position given by the index.
     * This operation will not leave a whole on the list as the elements
     * in a position bigger than the index will be moved forward.
     *
     * @param index Position at which the element needs to be removed
     * @return the element removed from the list
     * @throws IndexOutOfBoundsException if the index is out of the list bounds
     */
    @Override
    public T remove(int index) {
        return null;
    }

    /**
     * Removes the given element from the list if it is contained in it/
     *
     * @param elem the element to remove
     * @return whether the element was removed or not
     */
    @Override
    public boolean remove(T elem) {
        return false;
    }

    /**
     * Indicates whether the list has any elements
     *
     * @return {@code true} if the list has any elements, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Indicates whether the list contains the element
     *
     * @param element the element to search found
     * @return whether the element was found or not
     */
    @Override
    public boolean contains(T element) {
        return false;
    }
}
