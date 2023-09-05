package bg.softuni.bookshopspringdata.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface SeedService {

    void seedAuthors()throws IOException;
    void seedBooks()throws IOException;
    void seedCategories() throws IOException;

    default void seedAllData() throws IOException {
        seedCategories();
        seedAuthors();
        seedBooks();

    }
}
