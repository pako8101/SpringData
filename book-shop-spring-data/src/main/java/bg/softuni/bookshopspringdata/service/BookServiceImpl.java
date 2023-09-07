package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Book;
import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import bg.softuni.bookshopspringdata.domain.model.BookPrintInformation;
import bg.softuni.bookshopspringdata.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public void seedBooks(List<Book> books) {

        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public List<Book> getAllBooksAfterYear(LocalDate localdate) {
        final List<Book> allByReleaseDateAfter =
                this.bookRepository.findAllByReleaseDateAfter(localdate).get();
        System.out.println(allByReleaseDateAfter.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("\n")));
        return allByReleaseDateAfter;
    }

    @Override
    public List<Book> getAllBooksBeforeYear(LocalDate localdate) {

        final List<Book> books = this.bookRepository.findAllByReleaseDateBefore(localdate)
                .orElseThrow(NoSuchElementException::new);
        books.forEach(b -> System.out.println(b.getBookTitleEditionTypeAndPrice()));
        return books;


    }

    @Override
    public List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastname) {
        final List<Book> allBooksByAuthor = bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastname);
        allBooksByAuthor.stream()
                .map(Book::getBookTitleReleaseDateCopiesFormat)
                .forEach(System.out::println);

        return allBooksByAuthor;
    }

    @Override
    public List<Book> getAllByAgeRestriction(String ageRestriction) {
        final List<Book> allByAgeRestriction = this.bookRepository
                .findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));

        allByAgeRestriction.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);

        return allByAgeRestriction;
    }

    @Override
    public List<Book> getAllByEditionTypeAndCopiesOver(String editionType, Integer copiesToCompare) {

        final List<Book> books = this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType.toUpperCase()),
                        copiesToCompare);

        books.forEach(b -> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllByPriceBetween(BigDecimal lowBound, BigDecimal upperBound) {

        final List<Book> books =
                this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowBound, upperBound);

        books.forEach(b -> System.out.println(b.getFormattedBookPriceAndTitle()));
        return books;
    }

    @Override
    public List<Book> getAllNotReleasedInYear(Integer year) {
        // this.bookRepository.findAllByReleaseDate_Year(LocalDate.now());

        return null;
    }

    @Override
    public List<Book> getAllByTitleContaining(String contains) {

        final List<Book> allByTitleContaining = this.bookRepository
                .findAllByTitleContaining(contains);

        allByTitleContaining.forEach(b -> System.out.println(b.getTitle()));

        return allByTitleContaining;
    }

    @Override
    public List<Book> getAllByAuthorLastNameStartingWith(String prefix) {
        final List<Book> books = this.bookRepository.findAllByAuthorLastNameStartingWith(prefix);

        books.forEach(b -> System.out.println(b.getTitle()));

        return books;
    }

    @Override
    public Integer getAllByTitleGreaterThan(Integer length) {
        final Integer count = this.bookRepository.findAllByTitleGreaterThan(length);

        System.out.println(count);
        return count;
    }

    @Override
    public List<BookPrintInformation> getAllByTitle(String title) {

        final List<BookPrintInformation> allByTitle = this.bookRepository.findAllByTitle(title);

        allByTitle.forEach(System.out::println);
        return allByTitle;
    }

    @Override
    public void increaseCopiesBookReleasedAfter(Integer addedCopies, LocalDate dateAfter) {
        final List<Book> books = this.bookRepository.findAllByReleaseDateAfter(dateAfter).get();
        books.forEach(b -> b.setCopies(b.getCopies() + addedCopies));

        this.bookRepository.saveAllAndFlush(books);

        System.out.println(books.stream().mapToInt(Book::getCopies).sum());

//        for (Book book : books) {
//            book.setCopies(book.getCopies() + addedCopies);
//
//            this.bookRepository.saveAndFlush(book);
    }

    @Override
    public int deleteAllByCopiesLessThan(Integer copies) {
        return this.bookRepository.deleteAllByCopiesLessThan(copies);
    }

    @Override
    public int getBooksCountByAuthorFirstNameAndAuthorLastName(String fullName) {
        return this.bookRepository
                .getBooksCountByAuthorFirstNameAndAuthorLastName(fullName);
    }


}


