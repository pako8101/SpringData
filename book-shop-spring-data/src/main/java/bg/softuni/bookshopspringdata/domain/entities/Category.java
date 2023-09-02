package bg.softuni.bookshopspringdata.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "categories")
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;


    public Category() {

    }
}
