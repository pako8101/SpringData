package bg.softuni.bookshopspringdata.domain.entities;

import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends BaseEntity {

    @Column(length = 50,nullable = false)
    private String title;
    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    private EditionType editionType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;
    @ManyToMany
    private Set<Category> categories;

}
