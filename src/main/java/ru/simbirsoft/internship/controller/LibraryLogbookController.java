package ru.simbirsoft.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.internship.dto.LibraryLogbook;
import ru.simbirsoft.internship.repository.in_memory.LibraryLogbookRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/logbook")
public class LibraryLogbookController {

    @Autowired
    private LibraryLogbookRepository libraryLogbookRepository;

    @PostMapping
    public List<LibraryLogbook> save(@RequestBody @NotNull LibraryLogbook libraryLogbook) {
        return libraryLogbookRepository.save(libraryLogbook);
    }

    @GetMapping
    public List<LibraryLogbook> getAll() {
        return libraryLogbookRepository.getAll();
    }
}
