import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.util.List;

public class MarshalMain {
    public static void main(String[] args) throws JAXBException {
        AddressDTO addressDTO = new AddressDTO("Bulgaria","Sofia",10000);
        JAXBContext jaxbContext = JAXBContext.newInstance(AddressDTO.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(addressDTO,System.out);

        JAXBContext jaxbListContext = JAXBContext.newInstance(AddressesDTO.class);

        Marshaller listMarshaller = jaxbListContext.createMarshaller();
        marshaller.setProperty(listMarshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(new AddressesDTO(List.of(addressDTO,addressDTO)),System.out);
    }
}
