package org.example;

public class Person implements Comparable<Person> {
    protected String firstname;
    protected String surname;
    protected int age;
    public Person(int age, String surname, String firstname) {
        this.age = age;
        this.surname = surname;
        this.firstname = firstname;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge() {
        return age;
    }
    /**
     * The natural ordering will be determined by age in this example
     * @param o
     * @return
     */
    @Override
    public int compareTo(Person o) {
        return Integer.compare(age, o.age);
    }
    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
