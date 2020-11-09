package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;

public class Book {
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String name;
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String author;
    @NotNull
    @JsonView(View.INTERNAL.class)
    private Genre genre;

    public Book(String name, String author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!name.equals(book.name)) return false;
        if (!author.equals(book.author)) return false;
        return genre == book.genre;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                '}';
    }
}
