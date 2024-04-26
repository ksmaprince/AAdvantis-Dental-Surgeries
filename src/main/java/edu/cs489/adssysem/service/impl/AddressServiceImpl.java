package edu.cs489.adssysem.service.impl;

import edu.cs489.adssysem.dto.response.AddressResponse;
import edu.cs489.adssysem.exception.AddressNotFoundException;
import edu.cs489.adssysem.model.Address;
import edu.cs489.adssysem.repository.AddressRepository;
import edu.cs489.adssysem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        return addressRepository.findAll()
                .stream()
                .map(a -> new AddressResponse(
                        a.getAddressId(),
                        a.getStreet(),
                        a.getCity(),
                        a.getState(),
                        a.getZip()
                        )).toList();
    }

    @Override
    public Address getAddressById(Integer addressId)  throws AddressNotFoundException {
        return addressRepository.findById(addressId).orElseThrow(() ->
                new AddressNotFoundException(String.format("Error: Address with Id, %d, is not found.", addressId))
        );
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Integer addressId) {
        addressRepository.deleteById(addressId);
    }
}
