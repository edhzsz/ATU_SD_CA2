package org.example.collections.impl;

import org.example.Person;
import org.example.collections.IList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GenericIListTest {

    private static class EquitablePerson extends Person {
        public EquitablePerson(int age, String surname, String firstname) {
            super(age, surname, firstname);
        }

        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            EquitablePerson person = (EquitablePerson) o;
            // field comparison
            return Objects.equals(age, person.age)
                    && Objects.equals(surname, person.surname)
                    && Objects.equals(firstname, person.firstname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(surname, firstname, age);
        }
    }

    private static List<Person> persons;
    private static List<Person> equitablePersons;

    @BeforeAll
    static void initAll() {
        persons = new ArrayList<>() {
            {
                add(new Person(41, "Hernandez", "Edgar"));
                add(new Person(40, "Lopez", "Martha"));
                add(new Person(12, "Hernandez", "Luna"));
                add(new Person(1, "Hernandez", "Elessar"));
                add(new Person(20, "Hernandez", "Omar"));
                add(new Person(67, "Hernandez", "Carlos"));
                add(new Person(45, "Hernandez", "Carlos"));
                add(new Person(65, "Sanchez", "Silvia"));
                add(new Person(65, "Chavez", "Martha"));
                add(new Person(91, "Montes", "Eloisa"));
            }
        };

        equitablePersons = new ArrayList<>() {
            {
                add(new EquitablePerson(41, "Hernandez", "Edgar"));
                add(new EquitablePerson(40, "Lopez", "Martha"));
                add(new EquitablePerson(12, "Hernandez", "Luna"));
                add(new EquitablePerson(1, "Hernandez", "Elessar"));
            }
        };
    }

    private static List<IList<Person>> buildWithPersonsLists(Iterable<Person> persons) {
        List<IList<Person>> lists = new ArrayList<>() {
            {
                add(new GenericArrayList<>());
                add(new GenericLinkedList<>());
            }
        };

        for(IList<Person> l : lists) {
            for (Person p: persons) {
                l.add(p);
            }
        }

        return lists;
    }

    private static List<IList<Person>> emptyListsImplProvider() {
        return buildWithPersonsLists(new ArrayList<>());
    }

    private static List<IList<Person>> withOneElementListsImplProvider() {
        List<Person> onePerson = new ArrayList<>();
        onePerson.add(persons.get(0));

        return buildWithPersonsLists(onePerson);
    }

    private static List<IList<Person>> withAllPersonsListsImplProvider() {
        return buildWithPersonsLists(persons);
    }

    private static List<IList<Person>> withAllEquitablePersonsListsImplProvider() {
        return buildWithPersonsLists(equitablePersons);
    }

    @ParameterizedTest
    @MethodSource("emptyListsImplProvider")
    void a_new_list_is_empty(IList<Person> empty) {
        assertTrue(empty.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("withOneElementListsImplProvider")
    void a_list_with_one_element_is_not_empty(IList<Person> withOneElement) {
        assertFalse(withOneElement.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void a_list_with_multiple_elements_is_not_empty(IList<Person> personsList) {
        assertFalse(personsList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("emptyListsImplProvider")
    void size_of_a_new_list_zero(IList<Person> empty) {
        assertEquals(0, empty.size());
    }

    @ParameterizedTest
    @MethodSource("withOneElementListsImplProvider")
    void size_of_a_list_with_one_element_is_one(IList<Person> withOneElement) {
        assertEquals(1, withOneElement.size());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void size_of_a_list_with_multiple_elements_is_the_amount_of_elements(IList<Person> personsList) {
        assertEquals(persons.size(), personsList.size());
    }

    @ParameterizedTest
    @MethodSource("emptyListsImplProvider")
    void it_is_possible_to_add_the_same_element_twice(IList<Person> empty) {
        empty.add(persons.get(0));
        empty.add(persons.get(0));

        assertEquals(2, empty.size());
        assertEquals(persons.get(0), empty.get(0));
        assertEquals(persons.get(0), empty.get(1));
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void get_throws_IndexOutOfBoundsException_on_negative_index(IList<Person> instance) {
        assertThrows(IndexOutOfBoundsException.class, () ->
                instance.get(-5));
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void get_throws_IndexOutOfBoundsException_on_bigger_then_elements_index(IList<Person> instance) {
        assertThrows(IndexOutOfBoundsException.class, () ->
                instance.get(instance.size() + 10));
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void removing_by_index_throws_IndexOutOfBoundsException_on_negative_index(IList<Person> instance) {
        assertThrows(IndexOutOfBoundsException.class, () ->
                instance.remove(-10));
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void removing_by_index_IndexOutOfBoundsException_on_bigger_then_elements_index(IList<Person> instance) {
        assertThrows(IndexOutOfBoundsException.class, () ->
                instance.remove(instance.size() + 10));
    }

    @ParameterizedTest
    @MethodSource("withOneElementListsImplProvider")
    void removing_first_element_on_a_single_element_list_makes_it_empty(IList<Person> instance) {
        Person first = instance.remove(0);
        assertTrue(instance.isEmpty());
        assertEquals(persons.get(0), first);
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void removing_first_element_on_list_with_multiple_elements_does_not_makes_it_empty(IList<Person> instance) {
        instance.remove(0);
        assertFalse(instance.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void removing_second_element_on_list_with_multiple_elements_actually_removes_it(IList<Person> instance) {
        Person second = instance.get(1); // On 0-based index the second person is at index 1
        int originalSize = instance.size();

        instance.remove(1);

        assertEquals(originalSize - 1, instance.size());
        for (int i =0; i<instance.size(); i++) {
            assertNotEquals(second, instance.get(i));
        }
    }

    @ParameterizedTest
    @MethodSource("emptyListsImplProvider")
    void removing_by_element_on_empty_list_always_return_false(IList<Person> instance) {
        for (Person p : persons) {
            assertFalse(instance.remove(p));
        }
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void we_can_remove_all_elements_on_empty_list_always_by_element(IList<Person> instance) {
        for (Person p : persons) {
            assertTrue(instance.remove(p));
        }

        assertTrue(instance.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("withAllPersonsListsImplProvider")
    void cannot_remove_an_elements_reference_if_element_does_not_override_equals(IList<Person> instance) {
        Person secondPerson = persons.get(1);
        Person newSecondPerson = new Person(secondPerson.getAge(), secondPerson.getSurname(), secondPerson.getFirstname());

        assertTrue(instance.contains(secondPerson));
        assertFalse(instance.contains(newSecondPerson));
        assertFalse(instance.remove(newSecondPerson));
        assertTrue(instance.contains(secondPerson));
    }

    @ParameterizedTest
    @MethodSource("withAllEquitablePersonsListsImplProvider")
    void can_remove_an_element_if_element_does_override_equals(IList<Person> instance) {
        Person secondPerson = equitablePersons.get(1);
        Person newSecondPerson = new EquitablePerson(secondPerson.getAge(), secondPerson.getSurname(), secondPerson.getFirstname());

        assertTrue(instance.contains(secondPerson));
        assertTrue(instance.contains(newSecondPerson));
        assertTrue(instance.remove(newSecondPerson));
        assertFalse(instance.contains(secondPerson));
    }
}