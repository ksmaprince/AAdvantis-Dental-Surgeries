package edu.cs489.adssysem.controller;

import edu.cs489.adssysem.dto.response.AddressResponse;
import edu.cs489.adssysem.exception.AddressNotFoundException;
import edu.cs489.adssysem.model.Address;
import edu.cs489.adssysem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResponseEntity<List<AddressResponse>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer addressId) throws AddressNotFoundException {
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }
}
