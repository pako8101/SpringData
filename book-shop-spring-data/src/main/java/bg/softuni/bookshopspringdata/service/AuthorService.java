package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    boolean isDataSeeded();

    void seedAuthors(List<Author> authors);

    Author geRandomAuthor();

    List<Author> getAllAuthorsWithBooksBeforeYear(LocalDate date);

    List<Author> getAllAuthorsOrderedByBooksDesc();
}

