package ru.simbirsoft.internship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.internship.dto.Book;
import ru.simbirsoft.internship.repository.inMemory.BookRepository;
import ru.simbirsoft.internship.utils.View;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(produces = "application/json")
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @GetMapping(path = "/{author}", produces = "application/json")
    public List<Book> getBookByName(@PathVariable @NotNull String author) {
        return bookRepository.getByAuthor(author);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @JsonView(View.PUBLIC.class)
    public List<Book> saveBook(@RequestBody @NotNull Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam @NotNull String author,
                           @RequestParam @NotNull String name) {
        List<Book> booksByPerson = bookRepository.delete(author, name);
        if (!booksByPerson.isEmpty()) {
            throw new IllegalArgumentException("Book is taken by persons " + booksByPerson);
        }
    }
}
