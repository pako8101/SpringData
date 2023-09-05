package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    boolean isDataSeeded();

    void seedBooks(List<Book> books);
    List<Book> getAllBooksAfterYear(LocalDate date);


    List<Book> getAllBooksBeforeYear(LocalDate localdate);


    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastname);

    List<Book> getAllByAgeRestriction(String ageRestriction);

    List<Book> getAllByEditionTypeAndCopiesOver(String editionType, Integer copiesToCompare);

    List<Book> getAllByPriceBetween(BigDecimal lowBound, BigDecimal upperBound);
    List<Book> getAllNotReleasedInYear(Integer year);

    List<Book> getAllByTitleContaining(String contains);


}
