package ru.simbirsoft.internship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.internship.dto.Person;
import ru.simbirsoft.internship.repository.in_memory.PersonRepository;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(produces = "application/json")
    public List<Person> getAllPersons() {
        return personRepository.getAll();
    }

    @GetMapping(path = "/{lastName}", produces = "application/json")
    public List<Person> getPersonByName(@PathVariable @NotNull String lastName) {
        return personRepository.getByLastName(lastName);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @JsonView(View.PUBLIC.class)
    public List<Person> savePerson(@RequestBody @NotNull Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping
    public void deletePerson(@RequestParam @NotNull String lastName,
                             @RequestParam @NotNull String firstName) {
        List<Person> personWithBooks = personRepository.delete(lastName, firstName);
        if (!personWithBooks.isEmpty()) {
            throw new IllegalArgumentException("Person " + personWithBooks + " has books!");
        }
    }
}
