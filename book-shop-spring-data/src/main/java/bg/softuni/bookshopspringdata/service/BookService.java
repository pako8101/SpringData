package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Book;
import bg.softuni.bookshopspringdata.domain.model.BookPrintInformation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    List<Book> getAllByAuthorLastNameStartingWith(String prefix);
   Integer getAllByTitleGreaterThan(Integer length);

   List <BookPrintInformation> getAllByTitle(String length);

void increaseCopiesBookReleasedAfter(Integer addedCopies,LocalDate dateAfter);
    int deleteAllByCopiesLessThan(Integer copies);

    int getBooksCountByAuthorFirstNameAndAuthorLastName(String fullName);

}
