package bg.softuni.bookshopspringdata.domain.model;

import bg.softuni.bookshopspringdata.domain.enums.AgeRestriction;
import bg.softuni.bookshopspringdata.domain.enums.EditionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class BookPrintInformation {
    private String title;
    private EditionType editionType;
    private Integer age;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public BookPrintInformation(String title, EditionType editionType,AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.title + " "
                + this.editionType + " "
                + this.ageRestriction + " "
                + this.price;
    }
}
