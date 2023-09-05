package bg.softuni.bookshopspringdata.repositories;

import bg.softuni.bookshopspringdata.domain.entities.Book;
import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

//   @Query("select b from Book b where b.releaseDate != :year")
//    List<Book> findAllByReleaseDate_Year(LocalDate year);





}
