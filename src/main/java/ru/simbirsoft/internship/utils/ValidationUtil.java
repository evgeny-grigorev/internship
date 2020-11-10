package ru.simbirsoft.internship.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.simbirsoft.internship.dto.Book;
import ru.simbirsoft.internship.dto.LibraryLogbook;
import ru.simbirsoft.internship.dto.Person;
import ru.simbirsoft.internship.exceptions.NotFoundException;

import java.util.List;

public class ValidationUtil {
    private static final Logger log = LoggerFactory.getLogger(ValidationUtil.class);

    private ValidationUtil() {
    }

    public static <T> void checkInList(List<T> list, Object object) {
        if (!list.contains(object)) {
            String message = "No " + object.getClass().getName() + " " + object + " in list";
            log.error(message);
            throw new NotFoundException(message);
        }
    }

    public static <T> T checkInLibraryLogbook(List<LibraryLogbook> libraryLogbookList, T object) {
        if (object instanceof Book) {
            if (libraryLogbookList.stream()
                    .anyMatch(libraryLogbook -> libraryLogbook.getBook().equals(object))) {
                return object;
            }
        } else {
            if (!(object instanceof Person)) {
                return null;
            }
            if (libraryLogbookList.stream()
                    .anyMatch(libraryLogbook -> libraryLogbook.getPerson().equals(object))) {
                return object;
            }
        }
        return null;
    }
}
