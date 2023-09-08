package bg.softuni.modelmapperlab.service.impl;

import bg.softuni.modelmapperlab.DTO.CreateAddressDTO;
import bg.softuni.modelmapperlab.model.Address;
import bg.softuni.modelmapperlab.repository.AddressRepository;
import bg.softuni.modelmapperlab.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(ModelMapper modelMapper, AddressRepository addressRepository) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(CreateAddressDTO createAddressDTO) {

         Address address = modelMapper.map(createAddressDTO, Address.class);

        address = addressRepository.save(address);
        return address;
    }
}
