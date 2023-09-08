package bg.softuni.modelmapperlab.DTO;

public class CreateAddressDTO {

    private String country;
    private String city;

    public CreateAddressDTO() {
    }

    public CreateAddressDTO(String country, String city) {
        super();
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CreateAddressDTO{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
