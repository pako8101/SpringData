package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {

    @Column(nullable = false,unique = true)
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country>countries;

}
