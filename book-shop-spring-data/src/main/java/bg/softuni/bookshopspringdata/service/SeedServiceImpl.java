package bg.softuni.bookshopspringdata.service;

import bg.softuni.bookshopspringdata.domain.entities.Author;
import bg.softuni.bookshopspringdata.domain.entities.Book;
import bg.softuni.bookshopspringdata.domain.entities.Category;
import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.bookshopspringdata.constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {

    private final CategoryService categoryService;
    private final AuthorService authorService;

    private final BookService bookService;

    @Autowired
    public SeedServiceImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void seedAuthors() throws IOException {
        this.authorService.seedAuthors(
                Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
                        .stream().
                        map(fl -> Author.builder()
                                .firstName(fl.split(" ")[0])
                                .lastName(fl.split("")[1])
                                .build())
                        .toList());
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookService.isDataSeeded()) return;
        System.out.println();


     final List<Book> bookStream = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(row -> {
                    final String[] args = row.split("\\s");

                    final Author randomAuthor = this.authorService.geRandomAuthor();
                    final Set<Category> randomCategories = this.categoryService.getRandomCategories();

                    final String title = Arrays.stream(args)
                            .skip(5)
                            .collect(Collectors.joining());

                    final EditionType edition = EditionType.values()[Integer.parseInt(args[3])];
                    final AgeRestriction restriction = AgeRestriction.values()[Integer.parseInt(args[4])];
                    LocalDate releaseDate = LocalDate.parse(args[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));

                    int copies = Integer.parseInt(args[2]);
                    BigDecimal price = new BigDecimal(args[3]);


                   return Book.builder()
                            .author(randomAuthor).categories(randomCategories).title(title)
                            .editionType(edition).ageRestriction(restriction).releaseDate(releaseDate)
                            .copies(copies).price(price).build();


                }).toList();

     this.bookService.seedBooks(bookStream);
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryService.isDataSeeded()) return;

        this.categoryService.seedCategories(
                Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                        .stream().filter(s -> !s.isBlank())
                        .map(Category::new)
                        .toList());

    }
}
