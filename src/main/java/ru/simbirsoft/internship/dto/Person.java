package ru.simbirsoft.internship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String lastName;
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String middleName;
    @NotNull
    @JsonView(View.PUBLIC.class)
    private String firstName;
    @NotNull
    @JsonView(View.INTERNAL.class)
    private LocalDate birthDate;
}
