package bg.softuni.gamestoremappingexercise.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

   // private String
    @ManyToOne
    private User user;
    @ManyToMany(targetEntity = Game.class,fetch = FetchType.EAGER)
    private Set<Game> games;


}
