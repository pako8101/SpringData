package bg.softuni.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity

public class Bike extends Vehicle{
    private static final String BIKE_TYPE ="BIKE";
    public Bike(){
        super(BIKE_TYPE);
    }



}
