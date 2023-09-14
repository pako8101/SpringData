package bg.softuni.gamestoremappingexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class GameEditDto {


    private Long id;
    String title;
    BigDecimal price;
    Double size;
    String trailer;
    String thubnailURL;
    String description;
    LocalDate releaseDate;


    public String successfullyEditedGAme() {
        return "Edited " + this.title;
    }
    public void updateFields(Map<String,String> updatedValues){
        for (String key : updatedValues.keySet()) {
            if (Objects.equals(key, "title")){
                setTitle(updatedValues.get(key));
            } else if (Objects.equals(key, "price")){
                setPrice(new BigDecimal(updatedValues.get(key)));
                
            } else if (Objects.equals(key, "size")){
                setSize(Double.parseDouble(updatedValues.get(key)));

            } else if (Objects.equals(key, "trailer")){
                setTrailer(updatedValues.get(key));

            } else if (Objects.equals(key, "thubnailURL")){
                setThubnailURL(updatedValues.get(key));

            } else if (Objects.equals(key, "description")){
                setDescription(updatedValues.get(key));

            } else if (Objects.equals(key, "releaseDate")){
                setReleaseDate(LocalDate.now());

            }
        }
    }

}
