package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Player extends BaseEntity {

    @Column(nullable = false)
private String name;

    @Column(name = "squad_number", nullable = false)
   private short SquadNumber;

    @ManyToOne
   private  Team Team;

    @ManyToOne
   private Position Position;

    @Column(name = "is_currently_injured")
   private  boolean IsCurrentlyInjured;


}
