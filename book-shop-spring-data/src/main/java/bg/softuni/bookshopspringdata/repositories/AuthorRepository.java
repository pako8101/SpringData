package bg.softuni.bookshopspringdata.repositories;

import bg.softuni.bookshopspringdata.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


//    @Query("select a from  Author a order by size(a.books)")
//    List<Author> findAllDistinctOrderByBooksSize();

 //   List<Author>getAllDistinct();


    List<Author> findAllByFirstNameEndingWith(String suffix);
}

