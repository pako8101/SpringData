package bg.softuni.bookshopspringdata;

import bg.softuni.bookshopspringdata.service.AuthorService;
import bg.softuni.bookshopspringdata.service.BookService;
import bg.softuni.bookshopspringdata.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner;

    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
       // this.seedService.seedAllData();

//       final String[] input = this.scanner.nextLine().split("-");
//       final String first = input[0];
//       final String second =  input[1];
//       final String third =  input[2];


     //   this.bookService.getAllBooksAfterGivenYear( LocalDate.of(1999,1,1));
      //  this.authorService.getAllAuthorsWithBooksBeforeGivenYear(LocalDate.of(1991,1,1));

  // this.authorService.getAllAuthorsOrderedByBooksDesc();

  // this.bookService.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstname,lastName);

    //    this.bookService.getAllByAgeRestriction(firstname);

      //  this.bookService.getAllByEditionTypeAndCopiesOver("Gold", 5000);

     //   this.bookService.getAllByPriceBetween(BigDecimal.valueOf(5),BigDecimal.valueOf(40));

      //  this.bookService.getAllNotReleasedInYear(2000);
//        this.bookService
//                .getAllBooksBeforeYear(LocalDate.of(Integer.parseInt(third),Integer.parseInt(second),Integer.parseInt(first)));

       // authorService.getAllByFirstNameEndingWith("e");

        bookService.getAllByTitleContaining("e");


    }
}
