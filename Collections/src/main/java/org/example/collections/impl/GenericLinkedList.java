package org.example.collections.impl;

import org.example.collections.IList;
import org.example.collections.NoSuchElementException;

import java.util.Iterator;

/**
 * A generic list backed by a linked list. Implements the IList interface.
 * @param <T> Type of the elements that will be stored in this list.
 */
public class GenericLinkedList<T> implements IList<T> {
    /**
     *  Linked list Node.
     *
     */
    private class Node<T> {
        T data;
        Node<T> next;

        // Constructor to create a new node
        Node(T d) {
            data = d;
            next = null;
        }
    }

    private int elementsInList;

    private Node<T> head;

    private Node<T> tail;


    public GenericLinkedList() {
        elementsInList = 0;
        head = null;
        tail = null;
    }

    /**
     * Append to the end of the list
     *
     * @param elem element that will be appended to the end of the list
     */
    @Override
    public void add(T elem) {
        Node<T> node = new Node<T>(elem);

        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        elementsInList++;
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
        if (index < 0 || index > elementsInList) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> curr = head;
        int count = 0;

        while(count < index) {
            curr = curr.next;
            count++;
        }

        return curr.data;
    }

    /**
     * Returns the quantity of elements in the list
     *
     * @return the quantity of elements in the list
     */
    @Override
    public int size() {
        return elementsInList;
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
        if (index < 0 || index > elementsInList) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> prev = null;
        Node<T> curr = head;
        int count = 0;

        while(count < index) {
            prev = curr;
            curr = curr.next;
            count++;
        }

        if(prev == null) {
            head = curr.next;
        } else {
            prev.next = curr.next;
        }
        if(curr == tail) {
            tail = prev;
        }
        elementsInList--;
        return curr.data;
    }

    /**
     * Removes the given element from the list if it is contained in it.
     *
     * @param elem the element to remove
     * @return whether the element was removed or not
     */
    @Override
    public boolean remove(T elem) {
        Node<T> prev = null;
        Node<T> node = head;

        while(node != null) {
            if (node.data.equals(elem)) {
                if (prev == null) {
                    head = node.next;
                } else {
                    prev.next = node.next;
                }
                if (node == tail) {
                    tail = prev;
                }
                elementsInList--;
                return true;
            }
            prev = node;
            node = node.next;
        }

        return false;
    }

    /**
     * Indicates whether the list has any elements
     *
     * @return {@code true} if the list has any elements, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return elementsInList == 0;
    }

    /**
     * Indicates whether the list contains the element
     *
     * @param element the element to search found
     * @return whether the element was found or not
     */
    @Override
    public boolean contains(T element) {
        Node<T> node = head;

        while(node != null) {
            if (node.data.equals(element)) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new GenericLinkedListIterator();
    }


    private class GenericLinkedListIterator implements Iterator<T>{
        private Node<T> currentNode = head;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if(currentNode == null){
                throw new NoSuchElementException();
            }
            Node<T> temp = currentNode;
            currentNode = currentNode.next;

            return temp.data;
        }

        @Override
        //You do not have to provide functionality for the remove() method
        //We already have (non-iterator) mechanism for removing elements
        public void remove() {
            throw new UnsupportedOperationException("not supported yet");
        }
    }
}
