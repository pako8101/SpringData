import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class UnMarshalMain {
    public static void main(String[] args) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(AddressDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

  //    AddressDTO addressDTO = (AddressDTO) unmarshaller.unmarshal(System.in);
        JAXBContext jaxbListContext = JAXBContext.newInstance(AddressDTO.class);
        Unmarshaller unmarshallerList = jaxbListContext.createUnmarshaller();
        AddressesDTO addressesDTO = (AddressesDTO) unmarshallerList.unmarshal(System.in);
        System.out.println(addressesDTO);
    }
}
