import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressesDTO implements Serializable {

    @XmlElement(name = "addresses")
    private List<AddressDTO> addresses;

    public AddressesDTO() {
    }

    public AddressesDTO(List<AddressDTO> addresses){
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "AddressesDTO{" +
                "addresses=" + addresses +
                '}';
    }
}
