package bg.softuni.modelmapperlab.service;

import bg.softuni.modelmapperlab.DTO.CreateAddressDTO;
import bg.softuni.modelmapperlab.model.Address;

public interface AddressService {

    Address create(CreateAddressDTO createAddressDTO);
}
