package bg.softuni.entities;

import javax.persistence.*;

import java.math.BigDecimal;
@Entity
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy =  GenerationType.TABLE)
    protected Long Id;
    @Basic
    protected String Type;
    @Column
    protected  String Model;
    @Column
    protected BigDecimal Price;
    @Column(name = "fuel_type")
    protected   String fuelType;

    protected Vehicle(String type){
        this.Type = type;
    }

    protected Vehicle() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
