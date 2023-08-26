package bg.softuni.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

public class Car extends PassengerVehicle{

    private static final String CAR_TYPE ="CAR";


    public Car(){
        super(CAR_TYPE);
    }
    public Car(String model, String fuelType,int seats){

        this();

        this.Model = model;
        this.fuelType = fuelType;
        this.seats = seats;
    }


}
