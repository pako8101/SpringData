package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class BetGame implements Serializable {

    @OneToOne
    @Id
    private Game game;

    @OneToOne
    @Id
    private Bet bet;


    @OneToOne
    @JoinColumn
    private ResultPrediction  resultPrediction;




}
