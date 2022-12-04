package org.example.collections;

/**
 * Defines the contract for collections that behave as Lists
 * @param <T> Type of the elements that will be stored in this list
 */
public interface IList<T> extends Iterable<T> {
    /**
     * Append to the end of the list
     * @param elem element that will be appended to the end of the list
     */
    void add(T elem);

    /**
     * Gets the element at the given index.
     * @param index the position in the list
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of the list bounds
     */
    T get(int index);

    /**
     * Returns the quantity of elements in the list
      * @return the quantity of elements in the list
     */
    int size();

    /**
     * Removes an element at the position given by the index.
     * This operation will not leave a whole on the list as the elements
     * in a position bigger than the index will be moved forward.
     * @param index Position at which the element needs to be removed
     * @return the element removed from the list
     * @throws IndexOutOfBoundsException if the index is out of the list bounds
     */
    T remove(int index);

    /**
     * Removes the given element from the list if it is contained in it/
     * @param elem the element to remove
     * @return whether the element was removed or not
     */
    boolean remove(T elem);

    /**
     * Indicates whether the list has any elements
     * @return {@code true} if the list has any elements, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Indicates whether the list contains the element
     * @param element the element to search found
     * @return whether the element was found or not
     */
    boolean contains(T element);
}
