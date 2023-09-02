package bg.softuni.bookshopspringdata;

import bg.softuni.bookshopspringdata.service.AuthorService;
import bg.softuni.bookshopspringdata.service.BookService;
import bg.softuni.bookshopspringdata.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedService.seedAllData();

     //   this.bookService.getAllBooksAfterGivenYear( LocalDate.of(1999,1,1));
      //  this.authorService.getAllAuthorsWithBooksBeforeGivenYear(LocalDate.of(1991,1,1));

   this.authorService.getAllAuthorsOrderedByBooksDesc();
    }
}
