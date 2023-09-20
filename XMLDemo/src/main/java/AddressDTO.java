import java.io.Serializable;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDTO implements Serializable {

    @XmlAttribute
    private int population;
    @XmlElement
    private String country;
    @XmlElement
    private String city;

    public AddressDTO() {
    }

    public AddressDTO(String country, String city,int population) {
        this.country = country;
        this.city = city;
        this.population = population;
    }
}
