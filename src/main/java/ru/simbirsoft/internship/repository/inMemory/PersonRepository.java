package ru.simbirsoft.internship.repository.inMemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.internship.dto.LibraryLogbook;
import ru.simbirsoft.internship.dto.Person;
import ru.simbirsoft.internship.utils.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PersonRepository {

    private static final Logger log = LoggerFactory.getLogger(PersonRepository.class);
    private static List<Person> personList;

    static {
        personList = Stream.of(
                new Person("Uilams", "Robby", "Robby", LocalDate.parse("1974-02-13")),
                new Person("Collins", "Fil", "Fil", LocalDate.parse("1951-01-30")),
                new Person("Grol", "Deiv", "Deiv", LocalDate.parse("1969-01-14"))
        ).collect(Collectors.toList());
    }

    @Autowired
    private LibraryLogbookRepository libraryLogbookRepository;

    public List<Person> getAll() {
        return personList;
    }

    public List<Person> getByLastName(String lastName) {
        return personList.stream()
                .filter(person -> person.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    public List<Person> getByLastAndFirstName(String lastName, String firstName) {
        return personList.stream()
                .filter(person -> person.getLastName().equals(lastName))
                .filter(person -> person.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Person> save(Person person) {
        personList.add(person);
        return personList;
    }

    public List<Person> delete(String lastName, String firstName) {
        List<Person> personFilteredList = getByLastAndFirstName(lastName, firstName);
        List<Person> personWithBooksList = new ArrayList<>();
        List<LibraryLogbook> libraryLogbookList = libraryLogbookRepository.getAll();
        personFilteredList.forEach(person -> {
            Person personWithBook = ValidationUtil.checkInLibraryLogbook(libraryLogbookList, person);
            if (personWithBook != null) {
                personWithBooksList.add(personWithBook);
            } else {
                personList.remove(person);
            }
        });
        return personWithBooksList;
    }
}
