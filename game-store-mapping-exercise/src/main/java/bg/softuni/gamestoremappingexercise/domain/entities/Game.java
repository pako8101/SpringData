package bg.softuni.gamestoremappingexercise.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity{
    @Column(nullable = false)
    private String title;
    @Column
    private String trailer;
    @Column
    private String imageThumbnail;
    @Column
    private float size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private LocalDate releaseDate;

    public Game(String title, String trailer, String imageThumbnail, float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
