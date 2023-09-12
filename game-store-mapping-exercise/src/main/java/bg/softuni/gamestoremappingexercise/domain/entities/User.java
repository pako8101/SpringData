package bg.softuni.gamestoremappingexercise.domain.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false,length = 50)
    private String fullName;
    @Column(nullable = false)
    private boolean isAdmin;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Order.class,mappedBy = "user")
    private Set<Order> orders;


}
