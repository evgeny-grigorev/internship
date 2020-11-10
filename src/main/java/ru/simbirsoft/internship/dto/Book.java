package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
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
}
