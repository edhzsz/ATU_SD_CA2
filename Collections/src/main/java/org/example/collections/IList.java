package org.example.collections;

/**
 *
 * @param <T>
 */
public interface IList<T> {
    /**
     * add to the end of the list
     * @param elem
     */
    void add(T elem);

    /**
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException where appropriate
     */
    T get(int index);

    /**
     *
      * @return
     */
    int size();

    /**
     *
     * @param index
     * @return the element removed from the list
     * @throws IndexOutOfBoundsException where appropriate
     */
    T remove(int index);

    /**
     *
     * @param elem the element to remove
     * @return whether the elements was removed or not
     * @throws IndexOutOfBoundsException where appropriate
     */
    boolean remove(T elem);

    /**
     *
     * @return
     */
    boolean isEmpty();

    /**
     * This is new (to the interface) but should be straightforward to implement
     * @param element the element to search found
     * @return whether the element was found or not
     */
    boolean contains(T element);
}
