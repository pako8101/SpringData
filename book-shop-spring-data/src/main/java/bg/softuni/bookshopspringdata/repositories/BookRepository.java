package bg.softuni.bookshopspringdata.repositories;

import bg.softuni.bookshopspringdata.domain.entities.Book;
import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import bg.softuni.bookshopspringdata.domain.model.BookPrintInformation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate date);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastname);


    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copiesToCompare);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowBound, BigDecimal upperBound);

    List<Book> findAllByTitleContaining(String contains);

    List<Book> findAllByAuthorLastNameStartingWith(String prefix);

    @Query("select count(b) from  Book  b where length(b.title)> :length")
    Integer findAllByTitleGreaterThan(Integer length);

    @Query("select new bg.softuni.bookshopspringdata.domain.model" +
            ".BookPrintInformation(b.title,b.editionType,b.ageRestriction,b.price) from  Book b  where b.title = :title")
    List<BookPrintInformation> findAllByTitle(String title);


    @Query(value = "select * from book_shop_system.books b where year (b.release_date)  != 2000", nativeQuery = true)
    List<Book> findAllByReleaseDateYearNot(Integer year);

    @Modifying
    @Transactional
    @Query("update Book  b set b.copies = b.copies + :copiesAdded where b.releaseDate > :date")
    int updateBooksByCopies(Integer copiesAdded, LocalDate date);

    @Transactional
    int deleteAllByCopiesLessThan(Integer copies);

    @Procedure(value = "usp_get_books_written_by")
    int getBooksCountByAuthorFirstNameAndAuthorLastName(String fullName);

}
