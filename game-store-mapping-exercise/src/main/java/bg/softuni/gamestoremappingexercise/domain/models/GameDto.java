package bg.softuni.gamestoremappingexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class GameDto {

     String title ;
     BigDecimal price ;
     Double size ;
     String trailer ;
     String imageThumbnail ;
     String description ;
     LocalDate releaseDate ;

    public GameDto(String title, BigDecimal price, Double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
        
        
        validate();
    }

    private void validate() {
// TODO: validate field entries
    }
    public String successfullyAddedGAme(){
        return "Added " + this.title;
    }
}
