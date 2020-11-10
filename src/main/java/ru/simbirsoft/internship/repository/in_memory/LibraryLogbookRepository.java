package ru.simbirsoft.internship.repository.in_memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.internship.dto.LibraryLogbook;
import ru.simbirsoft.internship.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibraryLogbookRepository {
    private static List<LibraryLogbook> libraryLogbookList = new ArrayList<>();

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<LibraryLogbook> getAll() {
        return libraryLogbookList;
    }

    public List<LibraryLogbook> save(LibraryLogbook libraryLogbook) {
        if (libraryLogbookList.contains(libraryLogbook))
            throw new IllegalArgumentException("Object already in list" + libraryLogbook);
        libraryLogbookList.add(libraryLogbook);
        return libraryLogbookList;
    }

    public void delete(LibraryLogbook libraryLogbook) {
        ValidationUtil.checkInList(libraryLogbookList, libraryLogbook);
        libraryLogbookList.remove(libraryLogbook);
    }
}
