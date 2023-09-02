package bg.softuni.bookshopspringdata.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author extends BaseEntity {

    @Column(name = "first_name")

    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(targetEntity = Book.class,mappedBy = "author",fetch = FetchType.EAGER)
    public List<Book> books;

    public String getAuthorFullName(){
        return this.firstName + " " + this.lastName;
    }
    public String getAuthorFullNameAndCountOfBooks(){
        return this.firstName + " " + this.lastName + " " + this.books.size();
    }
}
