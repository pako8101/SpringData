package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    boolean isDataSeeded();

    void seedBooks(List<Book> books);
    List<Book> getAllBooksAfterGivenYear(LocalDate date);


    List<Book> getAllBooksBeforeYear(LocalDate localdate);
}
