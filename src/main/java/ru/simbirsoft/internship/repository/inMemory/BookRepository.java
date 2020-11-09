package ru.simbirsoft.internship.repository.inMemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.internship.dto.Book;
import ru.simbirsoft.internship.dto.Genre;
import ru.simbirsoft.internship.dto.LibraryLogbook;
import ru.simbirsoft.internship.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class BookRepository {

    private static List<Book> bookList;

    static {
        bookList = Stream.of(
                new Book("D'Artanyan & 3 muskets", "Duma", Genre.DETECTIVE),
                new Book("Tom Sayer", "Mark Twen", Genre.DETECTIVE),
                new Book("Fly Tsokotukha", "Korney Chukovsky", Genre.DRAMA)
        ).collect(Collectors.toList());
    }

    @Autowired
    private LibraryLogbookRepository libraryLogbookRepository;

    public List<Book> getAll() {
        return bookList;
    }

    public List<Book> getByAuthor(String author) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> save(Book book) {
        bookList.add(book);
        return bookList;
    }

    public List<Book> getByAuthorAndName(String author, String name) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equals(author))
                .filter(book -> book.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Book> delete(String author, String name) {
        List<Book> bookFilteredList = getByAuthorAndName(author, name);
        List<Book> booksWithPersonList = new ArrayList<>();
        List<LibraryLogbook> libraryLogbookList = libraryLogbookRepository.getAll();
        bookFilteredList.forEach(book -> {
            Book bookWithPerson = ValidationUtil.checkInLibraryLogbook(libraryLogbookList, book);
            if (bookWithPerson != null) {
                booksWithPersonList.add(bookWithPerson);
            } else {
                bookList.remove(book);
            }
        });
        return booksWithPersonList;
    }
}
