package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class LibraryLogbook {
    @NotNull
    private Person person;
    @NotNull
    private Book book;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    @NotNull
    private ZonedDateTime zonedDateTime;

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
