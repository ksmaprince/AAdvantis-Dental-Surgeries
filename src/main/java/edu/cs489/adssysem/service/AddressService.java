package edu.cs489.adssysem.service;

import edu.cs489.adssysem.dto.response.AddressResponse;
import edu.cs489.adssysem.exception.AddressNotFoundException;
import edu.cs489.adssysem.model.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);

    List<AddressResponse> getAllAddress();

    Address getAddressById(Integer addressId) throws AddressNotFoundException;

    Address updateAddress(Address address);

    void deleteAddressById(Integer addressId);
}
