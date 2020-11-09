package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Person {
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String lastName;
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String firstName;
    @NotNull
    @JsonView(View.INTERNAL.class)
    private LocalDate birthDate;

    public Person(String lastName, String firstName, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!lastName.equals(person.lastName)) return false;
        if (!firstName.equals(person.firstName)) return false;
        return birthDate.equals(person.birthDate);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
