package org.example.collections.impl;

import org.example.Person;
import org.example.collections.EmptyStackException;
import org.example.collections.IStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {
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

    private static List<IStack<Person>> buildWithPersonsStacks(Iterable<Person> persons) {
        List<IStack<Person>> stacks = new ArrayList<>() {
            {
                add(new GenericStack<>());
                add(new GenericStack<>(new GenericArrayList<>()));
                add(new GenericStack<>(new GenericLinkedList<>()));
            }
        };

        for(IStack<Person> s : stacks) {
            for (Person p: persons) {
                s.push(p);
            }
        }

        return stacks;
    }

    private static List<IStack<Person>> emptyStacksImplProvider() {
        return buildWithPersonsStacks(new ArrayList<>());
    }

    private static List<IStack<Person>> withOneElementStacksImplProvider() {
        List<Person> onePerson = new ArrayList<>();
        onePerson.add(persons.get(0));

        return buildWithPersonsStacks(onePerson);
    }

    private static List<IStack<Person>> withAllPersonsStacksImplProvider() {
        return buildWithPersonsStacks(persons);
    }

    @ParameterizedTest
    @MethodSource("emptyStacksImplProvider")
    void a_new_stack_is_empty(IStack<Person> empty) {
        assertTrue(empty.empty());
    }

    @ParameterizedTest
    @MethodSource("withOneElementStacksImplProvider")
    void a_new_stack_with_an_element_is_not_empty(IStack<Person> withOneElement) {
        assertFalse(withOneElement.empty());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsStacksImplProvider")
    void popping_all_elements_of_stack_makes_it_empty(IStack<Person> personsStack) {
        // Calling pop for each person in persons should
        // result in an empty array
        for (int i=0; i<persons.size(); i++) {
            personsStack.pop();
        }

        assertTrue(personsStack.empty());
    }


    @ParameterizedTest
    @MethodSource("emptyStacksImplProvider")
    void peek_on_an_empty_stack_throws_EmptyStackException(IStack<Person> empty) {
        assertThrows(EmptyStackException.class, () ->
                empty.peek());
    }

    @ParameterizedTest
    @MethodSource("withOneElementStacksImplProvider")
    void peek_on_a_stack_with_a_single_element_returns_the_only_element(IStack<Person> withOneElement) {
        assertEquals(persons.get(0), withOneElement.peek());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsStacksImplProvider")
    void peek_returns_the_last_element_pushed_into_the_stack(IStack<Person> personsStack) {
        Person lastPerson = persons.get(persons.size() - 1);
        assertEquals(lastPerson, personsStack.peek());
    }


    @ParameterizedTest
    @MethodSource("emptyStacksImplProvider")
    void pop_on_an_empty_stack_throws_EmptyStackException(IStack<Person> empty) {
        assertThrows(EmptyStackException.class, () ->
                empty.pop());
    }

    @ParameterizedTest
    @MethodSource("withOneElementStacksImplProvider")
    void pop_on_a_stack_with_a_single_element_returns_the_only_element(IStack<Person> withOneElement) {
        assertEquals(persons.get(0), withOneElement.pop());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsStacksImplProvider")
    void pop_returns_the_last_element_pushed_into_the_stack(IStack<Person> personsStack) {
        Person lastPerson = persons.get(persons.size() - 1);
        assertEquals(lastPerson, personsStack.pop());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsStacksImplProvider")
    void pop_returns_the_elements_in_last_in_first_out_order(IStack<Person> personsStack) {
        for (int i=persons.size() - 1; i >= 0; i--) {
            assertEquals(persons.get(i), personsStack.peek());
            assertEquals(persons.get(i), personsStack.pop());
        }
    }


    @ParameterizedTest
    @MethodSource("emptyStacksImplProvider")
    void is_possible_to_push_the_same_element_twice(IStack<Person> empty) {
        empty.push(persons.get(0));
        empty.push(persons.get(0));

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