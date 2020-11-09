package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class LibraryLogbook {
    @NotNull
    private Person person;
    @NotNull
    private Book book;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    @NotNull
    private ZonedDateTime zonedDateTime;

    public LibraryLogbook(Person person, Book book, ZonedDateTime zonedDateTime) {
        this.person = person;
        this.book = book;
        this.zonedDateTime = zonedDateTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Override
    public String toString() {
        return "LibraryLogbook{" +
                "person=" + person +
                ", book=" + book +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryLogbook that = (LibraryLogbook) o;

        if (!person.equals(that.person)) return false;
        return book.equals(that.book);
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + book.hashCode();
        return result;
    }
}
