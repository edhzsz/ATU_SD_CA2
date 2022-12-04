package org.example.collections.impl;

import org.example.collections.IList;

import java.util.Iterator;

/**
 * A generic list backed by an array. Implements the IList interface.
 * @param <T> Type of the elements that will be stored in this list.
 */
public class GenericArrayList<T> implements IList<T> {
    /**
     * This will hold our data - remember an ArrayList is nothing more than a managed array
     */
    private T[] buffer;

    /**
     * This will change as buffer fills up and we allocate more and more storage space
     */
    private int currentCapacity;

    /**
     * Index of next free location - will also help us to determine if the buffer is full
     */
    private int nextFreeLoc;

    private static final int INITIAL_CAPACITY = 4; // nice and small so that we test it quickly

    public GenericArrayList() {
        this(INITIAL_CAPACITY);
    }

    public GenericArrayList(int capacity) {
        currentCapacity = capacity;
        nextFreeLoc = 0;
        buffer = (T[])new Object[capacity];
    }

    /**
     * Append to the end of the list
     *
     * Each time you need to grow the array you should declare a temporary array
     * which is double the currentCapacity of buffer.
     *
     * Copy everything in buffer to tempArray
     *
     * Then update the buffer reference to refer to tempArray
     *
     * @param elem element that will be appended to the end of the list
     */
    @Override
    public void add(T elem) {
        growArrayIfNeeded();

        buffer[nextFreeLoc++] = elem;
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
        if (index < 0 || index >= nextFreeLoc) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        return buffer[index];
    }

    /**
     * Returns the quantity of elements in the list
     *
     * @return the quantity of elements in the list
     */
    @Override
    public int size() {
        return nextFreeLoc;
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
        if (index < 0 || index >= nextFreeLoc) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        T e = buffer[index];

        for( int i = index; i<nextFreeLoc -  1; i++)
        {
            buffer[i] = buffer[i+1];
        }

        nextFreeLoc--;

        return e;
    }

    /**
     * Removes the given element from the list if it is contained in it/
     *
     * @param elem the element to remove
     * @return whether the element was removed or not
     */
    @Override
    public boolean remove(T elem) {
        boolean matchFound = false;
        for (int index = 0; index < nextFreeLoc && !matchFound; index++)
        {
            if(buffer[index].equals(elem))
            {
                matchFound = true;

                //Close the gap - move elements 1 position to the left
                for( int i = index; i< buffer.length - 1; i++)
                {
                    buffer[i] = buffer[i+1];
                }

                nextFreeLoc--;
            }
        }

        return matchFound;
    }

    /**
     * Indicates whether the list has any elements
     *
     * @return {@code true} if the list has any elements, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return nextFreeLoc == 0;
    }

    /**
     * Indicates whether the list contains the element
     *
     * @param element the element to search found
     * @return whether the element was found or not
     */
    @Override
    public boolean contains(T element) {
        for(int i = 0; i<nextFreeLoc; i++) {
            T e = buffer[i];
            if(e.equals(element)){
                return true;
            }
        }

        return false;
    }

    /**
     * Private helper method to check if the currently allocated space is full.
     * If it is then it will allocate a bigger array, copy the contents, and set our
     * instance field (buffer) to refer to the newly allocated space.
     */
    private void growArrayIfNeeded()
    {
        if(nextFreeLoc == currentCapacity ) {
            // Allocate double the space - that will keep us going for a while
            int newCapacity = buffer.length * 2;
            T[] tempArr = (T[])new Object[newCapacity];
            currentCapacity = newCapacity;

            // copy from the old space into the new
            for(int i = 0; i < buffer.length; i++){
                tempArr[i] = buffer[i];
            }

            // Now, update so that our managed array points at the newly created array
            buffer = tempArr;
        }
    }

    @Override
    public String toString()
    {
        String data = "";
        for(int i = 0; i < nextFreeLoc; i++)
        {
            data += " " +buffer[i] + ",";
        }

        return "MyStringArrayList[" + data + " ]";
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
