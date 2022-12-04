package org.example.collections.impl;

import org.example.Person;
import org.example.collections.EmptyStackException;
import org.example.collections.IQueue;
import org.example.collections.IStack;
import org.example.collections.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericQueueTest {
    private static List<Person> persons;

    @BeforeAll
    static void initAll() {
        persons = new ArrayList<>() {
            {
                add(new Person(41, "Hernandez", "Edgar"));
                add(new Person(40, "Lopez", "Martha"));
                add(new Person(12, "Hernandez", "Luna"));
                add(new Person(1, "Hernandez", "Elessar"));
            }
        };
    }

    private static List<IQueue<Person>> buildWithPersonsQueues(Iterable<Person> persons) {
        List<IQueue<Person>> stacks = new ArrayList<>() {
            {
                add(new GenericQueue<>());
                add(new GenericQueue<>(new GenericArrayList<>()));
                add(new GenericQueue<>(new GenericLinkedList<>()));
            }
        };

        for(IQueue<Person> q : stacks) {
            for (Person p: persons) {
                q.enqueue(p);
            }
        }

        return stacks;
    }

    private static List<IQueue<Person>> emptyQueuesImplProvider() {
        return buildWithPersonsQueues(new ArrayList<>());
    }

    private static List<IQueue<Person>> withOneElementQueuesImplProvider() {
        List<Person> onePerson = new ArrayList<>();
        onePerson.add(persons.get(0));

        return buildWithPersonsQueues(onePerson);
    }

    private static List<IQueue<Person>> withAllPersonsQueuesImplProvider() {
        return buildWithPersonsQueues(persons);
    }
    @ParameterizedTest
    @MethodSource("emptyQueuesImplProvider")
    void a_new_queue_is_empty(IQueue<Person> empty) {
        assertTrue(empty.empty());
    }

    @ParameterizedTest
    @MethodSource("withOneElementQueuesImplProvider")
    void a_new_queue_with_an_element_is_not_empty(IQueue<Person> withOneElement) {
        assertFalse(withOneElement.empty());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsQueuesImplProvider")
    void dequeing_all_elements_of_stack_makes_it_empty(IQueue<Person> personsStack) {
        // Calling pop for each person in persons should
        // result in an empty array
        for (int i=0; i<persons.size(); i++) {
            personsStack.dequeue();
        }

        assertTrue(personsStack.empty());
    }

    @ParameterizedTest
    @MethodSource("emptyQueuesImplProvider")
    void first_on_an_empty_queue_throws_NoSuchElementException(IQueue<Person> empty) {
        assertThrows(NoSuchElementException.class, () ->
                empty.first());
    }

    @ParameterizedTest
    @MethodSource("withOneElementQueuesImplProvider")
    void first_on_a_stack_with_a_single_element_returns_the_only_element(IQueue<Person> withOneElement) {
        assertEquals(persons.get(0), withOneElement.first());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsQueuesImplProvider")
    void first_returns_the_first_element_pushed_into_the_stack(IQueue<Person> personsStack) {
        assertEquals(persons.get(0), personsStack.first());
    }

    @ParameterizedTest
    @MethodSource("emptyQueuesImplProvider")
    void dequeue_on_an_empty_stack_throws_NoSuchElementException(IQueue<Person> empty) {
        assertThrows(NoSuchElementException.class, () ->
                empty.dequeue());
    }

    @ParameterizedTest
    @MethodSource("withOneElementQueuesImplProvider")
    void dequeue_on_a_stack_with_a_single_element_returns_the_only_element(IQueue<Person> withOneElement) {
        assertEquals(persons.get(0), withOneElement.dequeue());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsQueuesImplProvider")
    void dequeue_returns_the_first_element_pushed_into_the_stack(IQueue<Person> personsStack) {
        assertEquals(persons.get(0), personsStack.dequeue());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsStacksImplProvider")
    void dequeue_returns_the_elements_in_first_in_first_out_order(IQueue<Person> personsStack) {
        for (int i=0; i < persons.size(); i++) {
            assertEquals(persons.get(i), personsStack.first());
            assertEquals(persons.get(i), personsStack.dequeue());
        }
    }
}