package org.example.collections.impl;

import org.example.collections.EmptyStackException;
import org.example.collections.IStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.Person;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {
    private IStack<Person> empty;

    private IStack<Person> personsStack;

    private IStack<Person> withOneElement;

    private static Person[] persons;

    @BeforeAll
    static void initAll() {
        persons = new Person[] {
                new Person(41, "Hernandez", "Edgar")
        };
    }

    @BeforeEach
    void setUp() {
        empty = new GenericStack<>();

        withOneElement = new GenericStack<>();
        withOneElement.push(persons[0]);

        personsStack = new GenericStack<>();
        for(Person p : persons) {
            personsStack.push(p);
        }
    }

    @Test
    void a_new_stack_is_empty() {
        assertTrue(empty.empty());
    }

    @Test
    void a_new_stack_with_an_element_is_not_empty() {
        assertFalse(withOneElement.empty());
    }

    @Test
    void popping_all_elements_of_stack_makes_it_empty() {
        // Calling pop for each person in persons should
        // result in an empty array
        for (int i=0; i<persons.length; i++) {
            personsStack.pop();
        }

        assertTrue(personsStack.empty());
    }

    @Test
    void peek_on_an_empty_stack_throws_EmptyStackException() {
        assertThrows(EmptyStackException.class, () ->
                empty.peek());
    }

    @Test
    void peek_on_a_stack_with_a_single_element_returns_the_only_element() {
        assertEquals(persons[0], withOneElement.peek());
    }

    @Test
    void peek_returns_the_last_element_pushed_into_the_stack() {
        Person lastPerson = persons[persons.length - 1];
        assertEquals(lastPerson, personsStack.peek());
    }

    @Test
    void pop_on_an_empty_stack_throws_EmptyStackException() {
        assertThrows(EmptyStackException.class, () ->
                empty.pop());
    }

    @Test
    void pop_on_a_stack_with_a_single_element_returns_the_only_element() {
        assertEquals(persons[0], withOneElement.pop());
    }

    @Test
    void pop_returns_the_last_element_pushed_into_the_stack() {
        Person lastPerson = persons[persons.length - 1];
        assertEquals(lastPerson, personsStack.pop());
    }

    @Test
    void pop_returns_the_elements_in_last_in_first_out_order() {
        for (int i=persons.length - 1; i >= 0; i--) {
            assertEquals(persons[i], personsStack.peek());
            assertEquals(persons[i], personsStack.pop());
        }
    }

    @Test
    void is_possible_to_push_the_same_element_twice() {
        empty.push(persons[0]);
        empty.push(persons[0]);

        // How many elements are in the stack?
        int count = 0;
        while(!empty.empty() && count < 200) { // guard against empty not implemented
            count++;
            empty.pop();
        }

        // we expect to have two elements
        // even if they were the same object
        assertEquals(2, count);
    }

}